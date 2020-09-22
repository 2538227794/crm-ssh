package com.yaorange.dao;

import com.yaorange.entity.Customer;

import java.util.List;

/**
 * @ClassNameCustomerDao
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/18 16:05
 * @Version 1.0
 **/
public interface CustomerDao extends BaseDao<Customer, Integer> {
    List<Object[]> selectBySql(String s, String date, Integer companyId);
}
