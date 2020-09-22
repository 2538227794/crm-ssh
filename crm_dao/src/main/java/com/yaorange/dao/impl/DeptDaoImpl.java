package com.yaorange.dao.impl;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yaorange.dao.BaseDao;
import com.yaorange.dao.DeptDao;
import com.yaorange.entity.Dept;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @ClassNameDeptDaoImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/9 7:45
 * @Version 1.0
 **/
@Repository("deptDao")
public class DeptDaoImpl extends BaseDaoImpl<Dept, Integer> implements DeptDao {

}
