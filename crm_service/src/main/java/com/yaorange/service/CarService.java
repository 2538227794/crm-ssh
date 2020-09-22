package com.yaorange.service;

import com.yaorange.entity.Car;
import com.yaorange.utils.Pagination;

/**
 * @ClassNameCarService
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 23:42
 * @Version 1.0
 **/
public interface CarService {
    void deleteCar(Integer[] ids);

    Pagination page(Integer id, Integer current, Integer pageSize);

    void addCar(Car car);

    void updateCar(Car car);
}
