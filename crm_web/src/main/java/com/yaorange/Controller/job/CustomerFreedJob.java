package com.yaorange.Controller.job;

import com.yaorange.service.CustomerService;

import javax.annotation.Resource;

/**
 * @ClassNameCustomerFreedJob
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/19 23:07
 * @Version 1.0
 **/
public class CustomerFreedJob {
    @Resource(name = "customerService")
    private CustomerService customerService;

    public void freed() {
        customerService.customerFreed();
    }
}
