package com.yaorange.service;


import com.yaorange.entity.Dept;
import com.yaorange.utils.Pagination;

import java.util.List;

/**
 * @ClassNameDeptService
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/9 7:42
 * @Version 1.0
 **/
public interface DeptService {
    /**
     * @param pageSize 每页条数
     * @param current  当前页数
     * @return Pagination 分页对象
     * @Author luokun
     * @Description 获取分页对象
     * @Date 2020/7/8 10:05
     **/
    Pagination getPages(Integer pageSize, Integer current);

    /**
     * @param dept 部门对象
     * @return null
     * @Author luokun
     * @Description 新增部门
     * @Date 2020/7/8 10:05
     **/
    void add(Dept dept);

    /**
     * @param ids
     * @return null
     * @Author luokun
     * @Description 根据ids删除部门对象
     * @Date 2020/7/8 10:05
     **/
    void delete(Integer[] ids);

    /**
     * @param dept 部门对象
     * @return null
     * @Author luokun
     * @Description 修改部门对象
     * @Date 2020/7/8 10:05
     **/
    void update(Dept dept);

    /**
     * @param id 公司id
     * @return List<Dept>部门集合
     * @Author luokun
     * @Description 根据公司id获取部门
     * @Date 2020/7/8 10:05
     **/
    List<Dept> getByCompany(Integer id);
}
