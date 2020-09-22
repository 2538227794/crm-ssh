package com.yaorange.webservice;

import javax.jws.WebService;

/**
 * @ClassNameWSInterface
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/20 15:26
 * @Version 1.0
 **/
@WebService
public interface WSInterface {
    String getState(String iphone);
}
