package com.yaorange.service.impl;

import com.yaorange.dao.CompanyDao;
import com.yaorange.entity.Company;
import com.yaorange.service.CompanyService;
import com.yaorange.utils.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassNameCompanyServiceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/9 14:57
 * @Version 1.0
 **/
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    @Resource(name = "companyDao")
    private CompanyDao companyDao;

    @Override
    public List<Company> getAll() {
        //公司状态可用
        final Integer state = 0;
        List<Company> companies = companyDao.getList("from Company where state=?1", 0);
        return companies;
    }

    @Override
    public List<Company> getList() {
        return companyDao.getList("from Company where state != ?1", 2);
    }

    @Override
    public Pagination getPage(Integer pageNo, Integer pageSize) {
        return companyDao.getPage("from Company where state !=?1 ", pageNo, pageSize, 2);
    }

    @Override
    public void add(Company company) {
        companyDao.insert(company);
    }

    @Override
    public void remove(Integer[] ids, String username) {
        for (Integer id : ids) {
            companyDao.update("update Company c set c.state=?1,c.updateBy=?2 where id=?3", 2, username, id);
        }
    }

    @Override
    public void update(Company company) {
        companyDao.update(company);
    }
}
