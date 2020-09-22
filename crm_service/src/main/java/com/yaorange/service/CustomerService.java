package com.yaorange.service;

import com.yaorange.entity.Customer;
import com.yaorange.utils.Pagination;

import java.util.List;

/**
 * @ClassNameCustomerService
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/18 7:55
 * @Version 1.0
 **/
public interface CustomerService {
    List<String> getRepeatPhones(List<String> customerPhones);

    void addExcelCustomer(Customer customer);

    Pagination getPages(Integer current, Integer pageSize, Integer empId);

    void assignAllEmp(Integer companyId, Integer deptId, Integer empId);

    void assignSelectEmp(Integer[] ids, Integer companyId, Integer deptId, Integer empId);

    void assignAllDept(Integer companyId, Integer deptId);

    /**
     * @param
     * @return
     * @Author luokun
     * @Description 任务调度释放客户资源到公共池
     * @Date 2020/7/8 10:05
     **/
    void customerFreed();

    List<Object[]> getMonthStatistics(Integer companyId, String format);

    Integer getStateByIphone(String phone);

    Pagination getPage(Integer current, Integer pageSize, Integer id);

    Pagination validCustomer(Integer current, Integer pageSize, Integer id);

    void update(Customer customer);

    Integer getCustomerNumber(Integer customerRosterId);

    Integer getCustomerUnallocated(Integer customerRosterId);

    List<Customer> getAdd(Integer addPersonId);

    Pagination getSelectPage(Integer customerRosterId, Integer addPersonId, Integer pageSize, Integer current);

    void addValidCustomer(Customer customer);

    void assignSingleEmp(Integer companyId, Integer deptId, Integer empId, Integer id);

    void assignSingleDept(Integer companyId, Integer deptId, Integer id);
}
