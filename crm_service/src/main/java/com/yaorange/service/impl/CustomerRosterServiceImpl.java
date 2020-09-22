package com.yaorange.service.impl;

import com.yaorange.dao.CustomerRosterDao;
import com.yaorange.entity.CustomerRoster;
import com.yaorange.service.CustomerRosterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassNameCustomerRosterServiceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/19 17:07
 * @Version 1.0
 **/
@Service("customerRosterService")
public class CustomerRosterServiceImpl implements CustomerRosterService {
    @Resource(name = "customerRosterDao")
    private CustomerRosterDao customerRosterDao;

    @Override
    public CustomerRoster add(CustomerRoster customerRoster) {
        CustomerRoster customerRosters = customerRosterDao.add(customerRoster);
        return customerRosters;
    }

    @Override
    public List<CustomerRoster> getRosters(Integer addPersonId) {
        List<CustomerRoster> customerRosters = customerRosterDao.getList("from CustomerRoster where addPersonId=?1", addPersonId);
        return customerRosters;
    }
}
