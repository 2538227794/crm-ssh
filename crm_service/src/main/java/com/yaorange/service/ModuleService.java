package com.yaorange.service;

import com.yaorange.entity.Module;
import com.yaorange.utils.Pagination;

import java.util.List;

/**
 * @ClassNameModuleService
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/12 23:48
 * @Version 1.0
 **/
public interface ModuleService {
    Pagination getPage(Integer pageSize, Integer pageNo);

    void delete(Integer[] ids);

    void add(Module module);

    void update(Module module);

    List<Module> getAll();

    List<Module> getModulesOneList();

}
