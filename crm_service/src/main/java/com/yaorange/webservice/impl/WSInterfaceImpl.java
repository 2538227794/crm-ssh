package com.yaorange.webservice.impl;

import com.yaorange.service.CustomerService;
import com.yaorange.webservice.WSInterface;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import javax.annotation.Resource;

/**
 * @ClassNameWSInterfaceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/20 15:27
 * @Version 1.0
 **/
public class WSInterfaceImpl implements WSInterface {
    @Resource(name = "customerService")
    private CustomerService customerService;


    @Override
    public String getState(String phone) {
        Integer state = customerService.getStateByIphone(phone);
        switch (state) {
            case 4:
                return "资料审核中";
            case 5:
                return "资料审核失败，请联系业务员重新上传资料";
            case 6:
                return "资料审核失败，因为你的信誉问题导致不能申请贷款";
            case 7:
                return "资料审核通过，等待放款中";
            default:
                return "未签单";
        }
    }
}
