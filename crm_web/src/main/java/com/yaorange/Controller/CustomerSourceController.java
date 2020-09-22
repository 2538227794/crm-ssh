package com.yaorange.Controller;

import com.yaorange.entity.CustomerSource;
import com.yaorange.service.CustomerSourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassNameCustomerSource
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 19:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("/customerSource")
public class CustomerSourceController {
    @Resource(name = "customerSourceService")
    private CustomerSourceService customerSourceService;

    @RequestMapping("/customerSourceList")
    public List<CustomerSource> getCustomerSourceList() {
        List<CustomerSource> customerSources = customerSourceService.getCustomerSourceList();
        return customerSources;
    }


}
