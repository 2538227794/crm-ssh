package com.yaorange.service.impl;

import com.yaorange.dao.HouseDao;
import com.yaorange.entity.House;
import com.yaorange.service.HouseService;
import com.yaorange.utils.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassNameHouseServiceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 22:53
 * @Version 1.0
 **/
@Service("houseService")
public class HouseServiceImpl implements HouseService {
    @Resource(name = "houseDao")
    private HouseDao houseDao;

    @Override
    public void updateHouse(House house) {
        houseDao.update(house);
    }

    @Override
    public void addHouse(House house) {
        houseDao.add(house);
    }

    @Override
    public Pagination page(Integer id, Integer current, Integer pageSize) {
        Pagination pagination = houseDao.getPage("from House where customerId=?1", current, pageSize, id);
        return pagination;
    }

    @Override
    public void deletehouse(Integer[] ids) {
        for (Integer id : ids) {
            houseDao.delete(id);
        }
    }
}
