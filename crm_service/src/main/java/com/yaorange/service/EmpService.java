package com.yaorange.service;

import com.yaorange.entity.Emp;
import com.yaorange.utils.Pagination;

import javax.mail.MessagingException;
import java.util.List;

/**
 * @ClassNameEmpService
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/11 17:44
 * @Version 1.0
 **/
public interface EmpService {
    Pagination getPages(Integer pageSize, Integer current);

    void add(Emp emp) throws MessagingException;

    void updateUsableState(Integer[] ids, Short state);

    void update(Emp emp);

    void assginRole(Integer[] ids, Integer id);

    Emp getEmpByUsername(String username);

    List<Emp> getByDept(Integer id);

}
