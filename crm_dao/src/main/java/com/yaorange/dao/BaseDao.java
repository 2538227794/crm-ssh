package com.yaorange.dao;

import com.yaorange.utils.Pagination;

import java.io.Serializable;
import java.util.List;

//封装通用方法
public interface BaseDao<T, ID extends Serializable> {
    /**
     * 获取数据总条数
     *
     * @param hql    必须的，语句
     * @param params 可选的，参数列表
     * @return
     */
    Integer getCount(String hql, Object... params);

    /**
     * 获取分页数据
     *
     * @param hql      必须的，语句
     * @param pageNo   必须的，页码
     * @param pageSize 必须的，分页大小
     * @param params   可选的，参数列表
     * @return
     */
    List<T> getPageList(String hql, Integer pageNo, Integer pageSize, Object... params);

    /**
     * 获取分页对象
     *
     * @param hql      必须的，语句
     * @param pageNo   必须的，页码
     * @param pageSize 必须的，分页大小
     * @param params   可选的，参数列表
     * @return
     */
    Pagination getPage(String hql, Integer pageNo, Integer pageSize, Object... params);

    /**
     * 获取集合数据
     *
     * @param hql    必须的，语句
     * @param params 可选的，参数列表
     * @return
     */
    List<T> getList(String hql, Object... params);

    /**
     * 新增操作
     *
     * @param t
     */
    void insert(T t);

    /**
     * 新增操作
     *
     * @param t
     */
    T add(T t);

    /**
     * 删除操作
     *
     * @param id
     */
    void delete(ID id);

    /**
     * 更新操作
     *
     * @param t
     */
    void update(T t);

    /**
     * 通过主键获取单个对象
     *
     * @return t
     */
    T get(ID id);

    /**
     * 通过主键修改单个对象
     *
     * @return t
     */
    void update(String hql, Object... params);

    /**
     * 获取单列集合
     **/
    List<String> getColumnList(String hql, Object... params);

    /**
     * 获取单个对象
     *
     * @return t
     */
    T get(String hql, Object... params);
}
