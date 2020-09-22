package com.yaorange.Controller;

import com.yaorange.entity.Emp;
import com.yaorange.service.EmpService;
import com.yaorange.utils.Pagination;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.List;

/**
 * @ClassNameEmpController
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/11 17:39
 * @Version 1.0
 **/
@RestController
@RequestMapping("/emp")
public class EmpController {
    @Resource(name = "empService")
    private EmpService empService;

    @GetMapping("/getPages")
    public Pagination getPages(Integer pageSize, Integer current) {
        Pagination pagination = empService.getPages(pageSize, current);
        return pagination;
    }

    @PostMapping("/add")
    public String add(@RequestBody Emp emp) throws MessagingException {
        //设置新增员工为在职状态
        emp.setState((short) 0);
        //设置新增员工是否可用
        emp.setUsableState((short) 0);
        empService.add(emp);
        return "ok";
    }

    @PostMapping("/update")
    public String update(@RequestBody Emp emp) {
        empService.update(emp);
        return "ok";
    }

    @PostMapping("/updateUsableState/{state}")
    public String updateUsableState(@RequestBody Integer[] ids, @PathVariable Short state) {
        empService.updateUsableState(ids, state);
        return "ok";
    }

    @PostMapping("/assginRole/{id}")
    public String assginRole(@RequestBody Integer[] ids, @PathVariable Integer id) {
        empService.assginRole(ids, id);
        return "ok";
    }

    @GetMapping("/getByDept/{id}")
    public List<Emp> getByDept(@PathVariable Integer id) {
        List<Emp> emps = empService.getByDept(id);
        return emps;
    }


}
