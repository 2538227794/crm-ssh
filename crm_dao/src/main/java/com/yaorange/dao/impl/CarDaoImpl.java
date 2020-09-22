package com.yaorange.dao.impl;

import com.yaorange.dao.CarDao;
import com.yaorange.entity.Car;
import org.springframework.stereotype.Repository;

/**
 * @ClassNameCarDaoImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 23:43
 * @Version 1.0
 **/
@Repository("carDao")
public class CarDaoImpl extends BaseDaoImpl<Car, Integer> implements CarDao {
}
