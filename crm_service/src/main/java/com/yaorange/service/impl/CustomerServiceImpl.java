package com.yaorange.service.impl;

import com.yaorange.dao.CustomerDao;
import com.yaorange.entity.Customer;
import com.yaorange.entity.Dept;
import com.yaorange.entity.Emp;
import com.yaorange.service.CustomerService;
import com.yaorange.utils.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassNameCustomerServiceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/18 7:55
 * @Version 1.0
 **/
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Resource(name = "customerDao")
    private CustomerDao customerDao;

    @Override
    public List<String> getRepeatPhones(List<String> customerPhones) {
        String phones = customerPhones.toString();
        List<String> repeatPhones = customerDao.getColumnList("SELECT phone FROM Customer where phone in (" + phones.substring(1, phones.length() - 1) + ")");
        return repeatPhones;
    }

    @Override
    public void addExcelCustomer(Customer customer) {
        customerDao.insert(customer);
    }

    @Override
    public Pagination getPages(Integer current, Integer pageSize, Integer empId) {
        System.out.println(empId);
        Pagination pagination = customerDao.getPage("from Customer c where c.addPersonId=?1 and c.dept.id is null", current, pageSize, empId);
        return pagination;
    }

    @Override
    public void assignAllEmp(Integer companyId, Integer deptId, Integer empId) {
        customerDao.update("update  Customer set company_id=?1,department_id=?2,employee_id=?3 ", companyId, deptId, empId);
    }

    @Override
    public void assignSelectEmp(Integer[] ids, Integer companyId, Integer deptId, Integer empId) {
        String s = Arrays.toString(ids);
        System.out.println(s.substring(1, s.length() - 1));
        customerDao.update("update  Customer set company_id=?1,department_id=?2,employee_id=?3 where id in(" + s.substring(1, s.length() - 1) + ")", companyId, deptId, empId);
    }

    @Override
    public void assignAllDept(Integer companyId, Integer deptId) {
        customerDao.update("update  Customer set company_id=?1,department_id=?2", companyId, deptId);
    }

    @Override
    public void customerFreed() {
        String hql = "from Customer where datediff(now(),fllow_date )>30 and state not in(4,5,6,7,8,9,10) and (visibility=1 or visibility is null) and public_view is null ";
        List<Customer> customers = customerDao.getList(hql);
        StringBuffer ids = new StringBuffer();
        for (Customer customer : customers) {
            ids.append(customer.getId());
            ids.append(",");
        }
        System.out.println(ids.substring(0, ids.length() - 1));
        hql = "update Customer set public_view=1,state=9,release_time=now(),release_type=1,release_id=0 where id in (" + ids.substring(0, ids.length() - 1) + ")";
        customerDao.update(hql);
    }

    @Override
    public List<Object[]> getMonthStatistics(Integer companyId, String date) {
        return customerDao.selectBySql("select add_person_name,count(id) from customer  where DATE_FORMAT(create_date, '%Y-%m-%d') >= ? AND DATE_FORMAT(create_date, '%Y-%m-%d') <= DATE_ADD(?,INTERVAL 1 MONTH) and company_id = ? GROUP BY add_person_name;", date, companyId);
    }

    @Override
    public Integer getStateByIphone(String phone) {
        Customer customer = customerDao.get("from Customer where phone=?1", phone);
        return customer.getState();
    }

    @Override
    public Pagination getPage(Integer current, Integer pageSize, Integer empId) {
        return customerDao.getPage("from Customer c where c.state=2 and c.emp.id=?1", current, pageSize, empId);
    }

    @Override
    public Pagination validCustomer(Integer current, Integer pageSize, Integer id) {
        return customerDao.getPage("from Customer c where c.emp.id=?1 and state = 3", current, pageSize, id);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public Integer getCustomerNumber(Integer customerRosterId) {
        Integer count = customerDao.getCount("from Customer where customerRoster.id=?1", customerRosterId);
        return count;
    }

    @Override
    public Integer getCustomerUnallocated(Integer customerRosterId) {
        Integer count = customerDao.getCount("from Customer where customerRoster.id=?1 and dept.id is null", customerRosterId);
        return count;
    }

    @Override
    public List<Customer> getAdd(Integer addPersonId) {
        //市场部录入
        final Integer addType = 0;
        List<Customer> customers = customerDao.getList("from Customer where addPersonId=?1 and addType=?2 and dept.id is null", addPersonId, addType);
        return customers;
    }

    @Override
    public Pagination getSelectPage(Integer customerRosterId, Integer addPersonId, Integer pageSize, Integer current) {
        Pagination pagination = customerDao.getPage("from Customer where customerRoster.id=?1 and addPersonId=?2 and dept is null", current, pageSize, customerRosterId, addPersonId);
        return pagination;
    }

    @Override
    public void addValidCustomer(Customer customer) {
        customerDao.add(customer);
    }

    @Override
    public void assignSingleEmp(Integer companyId, Integer deptId, Integer empId, Integer id) {
        Customer customer = customerDao.get(id);
        customer.setCompanyId(companyId);
        Dept dept = new Dept();
        dept.setId(deptId);
        customer.setDept(dept);
        Emp emp = new Emp();
        emp.setId(empId);
        customer.setEmp(emp);
    }

    @Override
    public void assignSingleDept(Integer companyId, Integer deptId, Integer id) {
        Customer customer = customerDao.get(id);
        customer.setCompanyId(companyId);
        Dept dept = new Dept();
        dept.setId(deptId);
        customer.setDept(dept);
    }


}
