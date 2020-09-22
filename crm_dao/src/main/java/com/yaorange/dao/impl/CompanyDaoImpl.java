package com.yaorange.dao.impl;

import com.yaorange.dao.CompanyDao;
import com.yaorange.entity.Company;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @ClassNameCompanyDaoImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/9 15:00
 * @Version 1.0
 **/
@Repository("companyDao")
public class CompanyDaoImpl extends BaseDaoImpl<Company, Integer> implements CompanyDao {
}
