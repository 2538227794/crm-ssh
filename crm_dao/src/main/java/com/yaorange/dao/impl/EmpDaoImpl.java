package com.yaorange.dao.impl;

import com.yaorange.dao.EmpDao;
import com.yaorange.entity.Emp;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @ClassNameEmpDaoImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/11 17:45
 * @Version 1.0
 **/
@Repository("empDao")
public class EmpDaoImpl extends BaseDaoImpl<Emp, Integer> implements EmpDao {
}
