package com.yaorange.service;

import com.yaorange.entity.Company;
import com.yaorange.utils.Pagination;

import java.util.List;

/**
 * @ClassNameCompanyService
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/9 14:56
 * @Version 1.0
 **/
public interface CompanyService {
    /**
     * @param null
     * @return List<Company> 公司对象集合
     * @Author luokun
     * @Description 获取所有公司对象
     * @Date 2020/7/8 10:05
     **/
    List<Company> getAll();

    List<Company> getList();

    Pagination getPage(Integer pageNo, Integer pageSize);

    void add(Company company);

    void update(Company company);

    void remove(Integer[] ids, String username);
}
