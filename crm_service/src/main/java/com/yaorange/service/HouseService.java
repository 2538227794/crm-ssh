package com.yaorange.service;

import com.yaorange.entity.House;
import com.yaorange.utils.Pagination;

/**
 * @ClassNameHouseService
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 22:53
 * @Version 1.0
 **/
public interface HouseService {

    void updateHouse(House house);

    void addHouse(House house);

    Pagination page(Integer id, Integer current, Integer pageSize);

    void deletehouse(Integer[] ids);
}
