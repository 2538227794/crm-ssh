package com.yaorange.Controller;

import java.util.ArrayList;

import com.yaorange.Controller.vo.RosterVo;
import com.yaorange.entity.Customer;
import com.yaorange.entity.CustomerRoster;
import com.yaorange.entity.CustomerSource;
import com.yaorange.entity.Emp;
import com.yaorange.service.CustomerRosterService;
import com.yaorange.service.CustomerService;
import com.yaorange.service.EmpService;
import com.yaorange.utils.Pagination;
import com.yaorange.utils.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassNameCustomerController
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/18 7:26
 * @Version 1.0
 **/
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource(name = "customerService")
    private CustomerService customerService;
    @Resource(name = "empService")
    private EmpService empService;
    @Resource(name = "customerRosterService")
    private CustomerRosterService customerRosterService;
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    @PostMapping("/updateCustomer")
    public String updateCustomer(@RequestBody Customer customer) {
        customerService.update(customer);
        return "ok";
    }

    @PostMapping("/market/addCustomer/{username}")
    public String addCustomer(@RequestBody Customer customer, @PathVariable String username) {
        if (customer.getCustomerSource().getId() == null) {
            customer.setCustomerSource(null);
        }
        Emp emp = empService.getEmpByUsername(username);
        customer.setAddPersonId(emp.getId());
        customer.setAddPersonName(emp.getName());
        customer.setState(0);
        customer.setAddType(0);
        customerService.addValidCustomer(customer);
        return "ok";
    }

    @PostMapping("/addValidCustomer/{username}")
    public String addValidCustomer(@RequestBody Customer customer, @PathVariable String username) {
        Emp emp = empService.getEmpByUsername(username);
        customer.setCompanyId(emp.getCompany().getId());
        customer.setCompanyName(emp.getCompany().getName());
        customer.setDept(emp.getDept());
        customer.setEmp(emp);
        customer.setState(3);
        customerService.addValidCustomer(customer);
        return "ok";
    }

    @PostMapping("/update")
    public String update(@RequestBody Customer customer) {
        customer.setState(3);
        customerService.update(customer);
        return "ok";
    }

    @PostMapping("setInvalid")
    public String setInvalid(@RequestBody Customer customer) {
        customer.setState(8);
        customerService.update(customer);
        return "ok";
    }

    @GetMapping("validCustomer/{pageSize}/{current}/{account}")
    public Pagination validCustomer(@PathVariable Integer current, @PathVariable Integer pageSize, @PathVariable String account) {
        //调用业务方法获取分页对象
        Emp emp = empService.getEmpByUsername(account);
        Pagination pagination = customerService.validCustomer(current, pageSize, emp.getId());
        return pagination;
    }

    @GetMapping("/page/{pageSize}/{current}/{account}")
    public Pagination page(@PathVariable Integer current, @PathVariable Integer pageSize, @PathVariable String account) {
        //调用业务方法获取分页对象
        Emp emp = empService.getEmpByUsername(account);
        Pagination pagination = customerService.getPage(current, pageSize, emp.getId());
        return pagination;
    }


    @GetMapping("/assignSingleDept/{companyId}/{deptId}/{id}")
    public String assignSingleDept(@PathVariable Integer companyId, @PathVariable Integer deptId, @PathVariable Integer id) {
        customerService.assignSingleDept(companyId, deptId, id);
        return "ok";
    }

    @GetMapping("/assignSingleEmp/{companyId}/{deptId}/{empId}/{id}")
    public String assignSingleEmp(@PathVariable Integer companyId, @PathVariable Integer deptId, @PathVariable Integer empId, @PathVariable Integer id) {
        customerService.assignSingleEmp(companyId, deptId, empId, id);
        return "ok";
    }

    @PostMapping("/assignSelectEmp/{companyId}/{deptId}/{empId}")
    public String assignSelectEmp(@RequestBody Integer[] ids, @PathVariable Integer companyId, @PathVariable Integer deptId, @PathVariable Integer empId) {
        customerService.assignSelectEmp(ids, companyId, deptId, empId);
        return "ok";
    }

    @GetMapping("/assignAllDept/{companyId}/{deptId}")
    public String assignAllDept(@PathVariable Integer companyId, @PathVariable Integer deptId) {
        customerService.assignAllDept(companyId, deptId);
        return "ok";
    }

    @GetMapping("/assignAllEmp/{companyId}/{deptId}/{empId}")
    public String assignAllEmp(@PathVariable Integer companyId, @PathVariable Integer deptId, @PathVariable Integer empId) {
        customerService.assignAllEmp(companyId, deptId, empId);
        return "ok";
    }

    @GetMapping("/getPages")
    public Pagination getPages(Integer current, Integer pageSize, String account) {
        Emp emp = empService.getEmpByUsername(account);
        Pagination pagination = customerService.getPages(current, pageSize, emp.getId());
        return pagination;
    }

    @GetMapping("/getAdd/{username}")
    public List<Customer> getAdd(@PathVariable String username) {
        Emp emp = empService.getEmpByUsername(username);
        List<Customer> customers = customerService.getAdd(emp.getId());
        return customers;
    }

    @GetMapping("/getSelectPage")
    public Pagination getSelectPage(Integer customerRosterId, String username, Integer pageSize, Integer current) {
        Emp emp = empService.getEmpByUsername(username);
        Pagination pagination = customerService.getSelectPage(customerRosterId, emp.getId(), pageSize, current);
        return pagination;
    }

    @GetMapping("/rosters/{username}")
    public List<RosterVo> getRosters(@PathVariable String username) {
        Emp emp = empService.getEmpByUsername(username);
        //获取订单集合
        List<CustomerRoster> rosters = customerRosterService.getRosters(emp.getId());
        List<RosterVo> rosterVos = new ArrayList<>();
        //组装rosterVo对象
        for (CustomerRoster roster : rosters) {
            RosterVo rosterVo = new RosterVo();
            rosterVo.setId(roster.getId());
            rosterVo.setName(roster.getName());
            rosterVo.setDatetime(roster.getDatetime());
            //获取订单下的顾客总条数
            Integer number = customerService.getCustomerNumber(roster.getId());
            rosterVo.setNumber(number);
            //获取未分配的顾客条数
            Integer unallocated = customerService.getCustomerUnallocated(roster.getId());
            rosterVo.setUnallocated(unallocated);
            rosterVo.setAllocated(number - unallocated);
            rosterVos.add(rosterVo);
        }
        return rosterVos;
    }

    @PostMapping("/importCustomer")
    public Map<String, Object> importCustomer(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException, InvalidFormatException {
        //返回的消息对象
        Map<String, Object> msgMap = new HashMap<>();
        msgMap.put("status", false);
        String customerRosterName = request.getParameter("customerRosterName");
        if (StringUtils.isEmpty(customerRosterName)) {
            msgMap.put("msg", "请输入客户名单名称");
            return msgMap;
        }
        String username = request.getParameter("username");
        if (StringUtils.isEmpty(username)) {
            msgMap.put("msg", "请登录后进行导入操作");
            return msgMap;
        }
        if (file.isEmpty()) {
            msgMap.put("msg", "请选择上传文件");
            return msgMap;
        }
        //通过POI读取上传表格文件，将数据新增到数据库中
        //获取上传文件的输入流
        InputStream inputStream = file.getInputStream();
        //使用POI解析上传表格文件
        Workbook workbook = WorkbookFactory.create(inputStream);
        //获取工作簿中的表
        Sheet sheet = workbook.getSheetAt(0);
        //判断文件数据是否为空
        int rowNum = sheet.getLastRowNum();
        //没有数据或只有标题行
        if (rowNum <= 1) {
            msgMap.put("msg", "上传的Excel文件没有数据");
            return msgMap;
        }
        //判断上传文件模板是否符合要求
        //判断是否有标题行
        Row firstRow = sheet.getRow(0);
        if (firstRow == null) {
            msgMap.put("msg", "上传的Excel文件,第一行必须有标题行");
            return msgMap;
        }
        //判断标题行是否有固定列
        short cellNum = firstRow.getLastCellNum();
        if (cellNum != 4) {
            msgMap.put("msg", "上传的Excel文件,数据列不满足要求，请核对模板添加数据");
            return msgMap;
        }
        //判断每列内容是否正确
        String cellValue1 = getStringCellValue(firstRow.getCell(0));
        String cellValue2 = getStringCellValue(firstRow.getCell(1));
        String cellValue3 = getStringCellValue(firstRow.getCell(2));
        String cellValue4 = getStringCellValue(firstRow.getCell(3));
        if (!"客户姓名".equals(cellValue1)) {
            msgMap.put("msg", "上传的Excel文件,第一行第一列必须为客户姓名");
            return msgMap;
        }
        if (!"手机号码".equals(cellValue2)) {
            msgMap.put("msg", "上传的Excel文件,第一行第二列必须为手机号码");
            return msgMap;
        }
        if (!"来源".equals(cellValue3)) {
            msgMap.put("msg", "上传的Excel文件,第一行第三列必须为来源");
            return msgMap;
        }
        if (!"备注".equals(cellValue4)) {
            msgMap.put("msg", "上传的Excel文件,第一行第四列必须为备注");
            return msgMap;
        }
        //创建客户对象集合
        List<Customer> customers = new ArrayList<Customer>();
        //准备用户存储所有新增的客户电话列表，用于循环读取时进行去重
        List<String> customerPhones = new ArrayList<String>();
        //记录一次上传中的无效个数
        int number = 0;
        //循环读取数据
        for (Row row : sheet) {
            //从第二行开发读取
            if (row.getRowNum() > 0) {
                Cell cell = null;
                //获取客户电话
                cell = row.getCell(1);
                String customerPhone = getStringCellValue(cell);
                //如果改行数据没有电话号码，就跳过
                if (StringUtils.isEmpty(customerPhone)) {
                    continue;
                }
                //获取客户姓名
                cell = row.getCell(0);
                String customerName = getStringCellValue(cell);
                //获取客户来源
                cell = row.getCell(2);
                String customerSource = getStringCellValue(cell);
                //获取备注
                cell = row.getCell(3);
                String otherInfo = getStringCellValue(cell);

                //组装客户对象
                Customer customer = new Customer();
                customer.setPhone(customerPhone);
                customer.setName(customerName);
                customer.setCustomerSource(new CustomerSource(Integer.parseInt(customerSource)));
                customer.setOtherInfo(otherInfo);
                //将一次上传中电话号码相同的去除掉
                if (!customerPhones.contains(customerPhone)) {
                    //添加电话到集合中
                    customerPhones.add(customerPhone);
                    //添加客户到集合
                    customers.add(customer);
                } else {
                    ++number;
                }
            }
        }
        System.out.println("一共循环读取" + customers.size() + "个不同客户数据");
        if (customers.size() == 0) {
            msgMap.put("msg", "上传的Excel文件,没有有效客户信息");
            return msgMap;
        }
        // 获取重复的客户电话集合
        List<String> repeatPhones = customerService.getRepeatPhones(customerPhones);
        System.out.println("重复的电话个数：" + (repeatPhones.size() + number));
        if (repeatPhones.size() == customerPhones.size()) {
            msgMap.put("msg", "上传的Excel文件,所有客户电话号码都已存在");
            return msgMap;
        }
        //记录添加成功的个数
        int successNum = 0;
        //记录添加失败的个数
        int errorNum = 0;
        //排除文件和数据库中重复电话的客户
        for (Customer customer : customers) {
            if (repeatPhones.contains(customer.getPhone())) {
                customers.remove(customer);
            }
        }
        //获取当前操作员工信息
        Emp emp = empService.getEmpByUsername(username);
        if (emp == null) {
            msgMap.put("msg", "当前登录账户不存在");
            return msgMap;
        }
        //新增名单对象
        CustomerRoster customerRoster = customerRosterService.add(new CustomerRoster(customerRosterName, emp.getId(), emp.getName(), new java.util.Date()));
        //获取导入类型参数
        Integer importType = Integer.parseInt(request.getParameter("importType"));
        //批量添加客户
        for (Customer customer : customers) {
            //获取电话
            String phone = customer.getPhone();
            //判断选择的导入类型
            if (importType == 9) {
                customer.setState(9);
            }
            // 设置客户相关默认值
            customer.setAddPersonId(emp.getId());//设置录入人员id
            customer.setAddPersonName(emp.getName());//设置录入人员名称
            customer.setAddType(1);//设置当前客户为批量导入客户
            customer.setCustomerRoster(customerRoster);//设置批量导入客户的名单名称
            customer.setCompanyId(emp.getCompany().getId());//设置客户所属公司id
            customer.setCompanyName(emp.getCompany().getName());//设置客户所属公司名称

            successNum++;//成功数量加1
        }
        //批量操作优化
        String sql = "insert into customer(attention_level, call_count,customer_level, name, other_info, phone, sign_state, state, visit_count, customer_source_id,add_person_id,add_person_name,add_type,customer_roster_id,company_id,company_name,create_date,create_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //准备录入日期和时间：需要SQL的Date类型

        Date date = new Date(new java.util.Date().getTime());
        Timestamp dateTime = new Timestamp(new java.util.Date().getTime());
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                //('C',0,'中','导入客户1','备注信息','18898765432','未签单',0,0,1)
                preparedStatement.setString(1, customers.get(i).getAttentionLevel());
                preparedStatement.setInt(2, customers.get(i).getCallCount());
                preparedStatement.setString(3, customers.get(i).getCustomerLevel());
                preparedStatement.setString(4, customers.get(i).getName());
                preparedStatement.setString(5, customers.get(i).getOtherInfo());
                preparedStatement.setString(6, customers.get(i).getPhone());
                preparedStatement.setString(7, customers.get(i).getSignState());
                preparedStatement.setInt(8, customers.get(i).getState());
                preparedStatement.setInt(9, customers.get(i).getVisitCount());
                preparedStatement.setInt(10, customers.get(i).getCustomerSource().getId());
                preparedStatement.setInt(11, customers.get(i).getAddPersonId());
                preparedStatement.setString(12, customers.get(i).getAddPersonName());
                preparedStatement.setInt(13, customers.get(i).getAddType());
                preparedStatement.setInt(14, customers.get(i).getCustomerRoster().getId());
                preparedStatement.setInt(15, customers.get(i).getCompanyId());
                preparedStatement.setString(16, customers.get(i).getCompanyName());
                preparedStatement.setDate(17, date);
                preparedStatement.setTimestamp(18, dateTime);
            }

            @Override
            public int getBatchSize() {
                return customers.size();
            }
        });
        //标识上传成功
        msgMap.put("status", true);
        //重复个数
        msgMap.put("repeatCount", repeatPhones.size() + number);
        //新增失败
        msgMap.put("errorCount", errorNum);
        //新增成功
        msgMap.put("successCount", successNum);
        return msgMap;
    }

    //工具方法
    private String getStringCellValue(Cell cell) {
        if (Objects.isNull(cell)) {
            return "";
        }

        String value = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                //如果为时间格式的内容
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                    break;
                } else {
                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                }
                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                value = cell.getBooleanCellValue() + "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                value = cell.getCellFormula() + "";
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                value = "";
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;
        }
        return value;
    }

}

