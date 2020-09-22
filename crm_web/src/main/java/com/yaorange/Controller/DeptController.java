package com.yaorange.Controller;

import com.yaorange.entity.Dept;
import com.yaorange.service.DeptService;
import com.yaorange.utils.Pagination;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassNameDeptController
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/9 7:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Resource(name = "deptService")
    private DeptService deptService;

    @ResponseBody
    @GetMapping("/getPages")
    public Pagination getPages(Integer pageSize, Integer current) {
        //调用业务方法获取分页对象
        Pagination pagination = deptService.getPages(pageSize, current);
        return pagination;
    }

    @PostMapping("/add")
    public String add(@RequestBody Dept dept) {
        //判断部门名称不能为空
        if (dept.getName().equals("") || dept.getName() == null) {
            return "false";
        }
//        //设置创建时间
//        dept.setCreateTime(new Date());
        //调用业务方法
        deptService.add(dept);
        return "ok";
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Integer[] ids) {
        //调用业务方法
        deptService.delete(ids);
        return "ok";
    }

    @PostMapping("/update")
    public String update(@RequestBody Dept dept) {
        //判断部门名称不能为空
        if (dept.getName().equals("") || dept.getName() == null) {
            return "false";
        }
//        //设置修改时间
//        dept.setUpdateTime(new Date());
        //调用业务方法
        deptService.update(dept);
        return "ok";
    }

    /**
     * @param id 公司id
     * @return List<Dept> 部门集合
     * @Author
     * @Description 根据公司id获取部门
     * @Date 2020/7/8 10:05
     **/
    @GetMapping("/getByCompany/{id}")
    public List<Dept> getByCompany(@PathVariable Integer id) {
        List<Dept> depts = deptService.getByCompany(id);
        return depts;
    }
}
