package com.yaorange.dao.impl;

import com.yaorange.dao.ModuleDao;
import com.yaorange.entity.Module;
import org.springframework.stereotype.Repository;

/**
 * @ClassNameModuleDaoImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/12 23:50
 * @Version 1.0
 **/
@Repository("moduleDao")
public class ModuleDaoImpl extends BaseDaoImpl<Module, Integer> implements ModuleDao {
}
