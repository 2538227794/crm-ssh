package com.yaorange.service.impl;

import com.yaorange.dao.CarDao;
import com.yaorange.dao.CreditCardDao;
import com.yaorange.entity.Car;
import com.yaorange.service.CarService;
import com.yaorange.utils.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassNameCarServiceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 23:42
 * @Version 1.0
 **/
@Service("carService")
public class CarServiceImpl implements CarService {
    @Resource(name = "carDao")
    private CarDao carDao;

    @Override
    public void deleteCar(Integer[] ids) {
        for (Integer id : ids) {
            carDao.delete(id);
        }
    }

    @Override
    public Pagination page(Integer id, Integer current, Integer pageSize) {
        Pagination pagination = carDao.getPage("from Car where customerId=?1", current, pageSize, id);
        return pagination;
    }

    @Override
    public void addCar(Car car) {
        carDao.add(car);
    }

    @Override
    public void updateCar(Car car) {
        carDao.update(car);
    }
}
