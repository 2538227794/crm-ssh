package com.yaorange.Controller;


import com.yaorange.entity.Role;
import com.yaorange.service.RoleService;
import com.yaorange.utils.Pagination;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassNameRoleController
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/12 20:42
 * @Version 1.0
 **/
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource(name = "roleService")
    private RoleService roleService;

    @RequestMapping("/list")
    public List<Role> getAll() {
        List<Role> roles = roleService.getAll();
        return roles;
    }

    @RequestMapping("/getPage")
    public Pagination getPage(Integer pageSize, Integer current) {
        Pagination pagination = roleService.getPage(pageSize, current);
        return pagination;
    }

    @PostMapping("/add")
    public String add(@RequestBody Role role) {
        roleService.add(role);
        return "ok";
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Integer[] ids) {
        roleService.delete(ids);
        return "ok";
    }

    @PostMapping("/update")
    public String update(@RequestBody Role role) {
        roleService.update(role);
        return "ok";
    }


}
