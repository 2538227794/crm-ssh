package com.yaorange.dao.impl;

import com.yaorange.dao.HouseDao;
import com.yaorange.entity.House;
import org.springframework.stereotype.Repository;

/**
 * @ClassNameHouseDaoImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 22:59
 * @Version 1.0
 **/
@Repository("houseDao")
public class HouseDaoImpl extends BaseDaoImpl<House, Integer> implements HouseDao {
}
