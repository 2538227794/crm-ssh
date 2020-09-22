package com.yaorange.dao.impl;

import com.yaorange.dao.BaseDao;
import com.yaorange.dao.RoleDao;
import com.yaorange.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * @ClassNameRoleDaoImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/12 20:50
 * @Version 1.0
 **/
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, Integer> implements RoleDao {
}
