package com.yaorange.dao.impl;

import com.yaorange.dao.CustomerRosterDao;
import com.yaorange.entity.CustomerRoster;
import org.springframework.stereotype.Repository;

/**
 * @ClassNameCustomerRosterDaoImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/19 17:11
 * @Version 1.0
 **/
@Repository("customerRosterDao")
public class CustomerRosterDaoImpl extends BaseDaoImpl<CustomerRoster, Integer> implements CustomerRosterDao {
}
