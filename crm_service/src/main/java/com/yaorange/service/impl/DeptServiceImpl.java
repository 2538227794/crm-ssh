package com.yaorange.service.impl;

import com.yaorange.dao.DeptDao;
import com.yaorange.entity.Dept;
import com.yaorange.service.DeptService;
import com.yaorange.utils.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassNameDeptServiceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/9 7:42
 * @Version 1.0
 **/
@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Resource(name = "deptDao")
    private DeptDao deptDao;

    @Override
    public Pagination getPages(Integer pageSize, Integer current) {
        Pagination pagination = deptDao.getPage("from Dept", current, pageSize);
        return pagination;
    }


    @Override
    public void add(Dept dept) {
        //调用Dao方法
        deptDao.insert(dept);
    }

    @Override
    public void delete(Integer[] ids) {

        //遍历数组根据每个id删除对象
        for (Integer id : ids) {
            //调用Dao方法
            deptDao.delete(id);
        }
    }

    @Override
    public void update(Dept dept) {
        //调用Dao方法
        deptDao.update(dept);
    }

    @Override
    public List<Dept> getByCompany(Integer id) {
        List<Dept> depts = deptDao.getList("from Dept where company.id=?1", id);
        return depts;
    }
}
