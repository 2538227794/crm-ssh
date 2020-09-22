package com.yaorange.dao.impl;

import com.yaorange.dao.BaseDao;
import com.yaorange.utils.Pagination;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    public BaseDaoImpl() {
        //获取当前类对象的父类Class
        Type superclass = this.getClass().getGenericSuperclass();
        //类参数数据类型数组，也就是<>中的数据类型
        Type[] typeArguments = ((ParameterizedType) superclass).getActualTypeArguments();
        // 将类参数类型转换为字节码
        entityClass = (Class<T>) typeArguments[0];
    }


    /**
     * 用于继承子类获取EntityManager进行特有方法编写
     **/
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Integer getCount(String hql, Object... params) {
        hql = "select count(*) " + hql;
        Query query = entityManager.createQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                //约定占位使用?序号，序号统一从1开始
                query.setParameter((i + 1), params[i]);
            }
        }
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    @Override
    public List<T> getPageList(String hql, Integer pageNo, Integer pageSize, Object... params) {
        Query query = entityManager.createQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                //约定占位使用?序号，序号统一从1开始
                query.setParameter((i + 1), params[i]);
            }
        }
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<T> list = query.getResultList();
        return list;
    }

    @Override
    public Pagination getPage(String hql, Integer pageNo, Integer pageSize, Object... params) {
        Integer total = getCount(hql, params);
        Pagination pagination = new Pagination(pageNo, pageSize, total);
        List<T> pageList = getPageList(hql, pagination.getPageNo(), pageSize, params);
        pagination.setList(pageList);
        return pagination;
    }

    @Override
    public List<T> getList(String hql, Object... params) {
        Query query = entityManager.createQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                //约定占位使用?序号，序号统一从1开始
                query.setParameter((i + 1), params[i]);
            }
        }
        List<T> list = query.getResultList();
        return list;
    }

    @Override
    public List<String> getColumnList(String hql, Object... params) {
        Query query = entityManager.createQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                //约定占位使用?序号，序号统一从1开始
                query.setParameter((i + 1), params[i]);
            }
        }
        List<String> list = query.getResultList();
        return list;
    }

    @Override
    public T get(String hql, Object... params) {
        Query query = entityManager.createQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter((i + 1), params[i]);
            }
        }
        return (T) query.getSingleResult();
    }

    @Override
    public void insert(T t) {
        entityManager.persist(t);
    }

    @Override
    public T add(T t) {
        T ob = entityManager.merge(t);
        return ob;
    }

    @Override
    public void delete(ID id) {
        T t = get(id);
        entityManager.remove(t);
    }

    @Override
    public void update(T t) {
        entityManager.merge(t);
    }

    @Override
    public T get(ID id) {
        T t = entityManager.find(entityClass, id);
        return t;
    }

    @Override
    public void update(String hql, Object... params) {
//        entityClass.getSimpleName():获取运行时的实体类名
        Query query = entityManager.createQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter((i + 1), params[i]);//约定占位使用?序号，序号统一从1开始
            }
        }
        int i = query.executeUpdate();
    }

}
