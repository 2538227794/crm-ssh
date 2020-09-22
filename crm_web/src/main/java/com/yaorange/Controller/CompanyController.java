package com.yaorange.Controller;

import com.yaorange.entity.Company;
import com.yaorange.service.CompanyService;
import com.yaorange.utils.Pagination;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassNameCompanyController
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/9 14:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Resource(name = "companyService")
    private CompanyService companyService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Company> getAll() {
        //调用业务方法
        List<Company> companies = companyService.getAll();
        return companies;
    }

    @GetMapping("/page")
    public Pagination page(Integer current, Integer pageSize) {
        //调用业务方法获取分页对象
        Pagination pagination = companyService.getPage(current, pageSize);
        return pagination;
    }

    @PostMapping("/add")
    public String add(@RequestBody Company company) {
        companyService.add(company);
        return "ok";
    }

    @PostMapping("/update")
    public String update(@RequestBody Company company) {
        companyService.update(company);
        return "ok";
    }

    @PostMapping("/delete/{username}")
    public String remove(@RequestBody Integer[] ids, @PathVariable String username) {
        companyService.remove(ids, username);
        return "ok";
    }
}
