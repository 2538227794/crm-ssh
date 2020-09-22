package com.yaorange.service;

import com.yaorange.entity.CustomerRoster;

import java.util.List;

/**
 * @ClassNameCustomerRosterService
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/19 17:06
 * @Version 1.0
 **/
public interface CustomerRosterService {
    CustomerRoster add(CustomerRoster customerRoster);

    List<CustomerRoster> getRosters(Integer addPersonId);
}
