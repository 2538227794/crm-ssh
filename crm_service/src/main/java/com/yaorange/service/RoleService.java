package com.yaorange.service;

import com.yaorange.entity.Role;
import com.yaorange.utils.Pagination;

import java.util.List;

/**
 * @ClassNameRoleService
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/12 20:48
 * @Version 1.0
 **/
public interface RoleService {
    List<Role> getAll();

    Pagination getPage(Integer pageSize, Integer current);

    void add(Role role);

    void delete(Integer[] ids);

    void update(Role role);
}
