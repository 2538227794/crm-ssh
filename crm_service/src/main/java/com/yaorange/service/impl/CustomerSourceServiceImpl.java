package com.yaorange.service.impl;

import com.yaorange.dao.CustomerSourceDao;
import com.yaorange.entity.CustomerSource;
import com.yaorange.service.CustomerSourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassNameCustomerSourceServiceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 19:56
 * @Version 1.0
 **/
@Service("customerSourceService")
public class CustomerSourceServiceImpl implements CustomerSourceService {
    @Resource(name = "customerSourceDao")
    private CustomerSourceDao customerSourceDao;

    @Override
    public List<CustomerSource> getCustomerSourceList() {
        List<CustomerSource> customerSources = customerSourceDao.getList("from CustomerSource");
        return customerSources;
    }
}
