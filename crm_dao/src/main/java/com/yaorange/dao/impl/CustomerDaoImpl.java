package com.yaorange.dao.impl;

import com.yaorange.dao.CustomerDao;
import com.yaorange.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * @ClassNameCustomerDaoImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/18 16:05
 * @Version 1.0
 **/
@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer, Integer> implements CustomerDao {


    @Override
    public List<Object[]> selectBySql(String s, String date, Integer companyId) {
        Query nativeQuery = getEntityManager().createNativeQuery(s);
        nativeQuery.setParameter(1, date);
        nativeQuery.setParameter(2, date);
        nativeQuery.setParameter(3, companyId);
        List list = nativeQuery.getResultList();
        return list;
    }
}
