package com.yaorange.service.impl;

import com.yaorange.dao.RoleDao;
import com.yaorange.entity.Role;
import com.yaorange.service.RoleService;
import com.yaorange.utils.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassNameRoleServiceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/12 20:48
 * @Version 1.0
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource(name = "roleDao")
    private RoleDao roleDao;

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleDao.getList("from Role");
        return roles;
    }

    @Override
    public Pagination getPage(Integer pageSize, Integer current) {
        Pagination pagination = roleDao.getPage("from Role ", current, pageSize);
        return pagination;
    }

    @Override
    public void add(Role role) {
        roleDao.insert(role);
    }

    @Override
    public void delete(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            roleDao.delete(ids[i]);
        }
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }
}
