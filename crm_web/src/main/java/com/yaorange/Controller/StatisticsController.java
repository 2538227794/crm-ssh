package com.yaorange.Controller;

import com.yaorange.Controller.vo.PieStatVo;
import com.yaorange.entity.Emp;
import com.yaorange.service.CustomerService;
import com.yaorange.service.EmpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 **/
@RestController
@RequestMapping("/stat")
public class StatisticsController {
    @Resource(name = "empService")
    private EmpService empService;
    @Resource(name = "customerService")
    private CustomerService customerService;

    @GetMapping("/monthData/{account}/{monthTime}")
    public List<PieStatVo> monthData(@PathVariable String account, @PathVariable Date monthTime) {
        Emp emp = empService.getEmpByUsername(account);
        Integer companyId = emp.getCompany().getId();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-01");
        String format = simpleDateFormat.format(monthTime);
        System.out.println(format);
        List<Object[]> monthStatistics = customerService.getMonthStatistics(companyId, format);
        ArrayList<PieStatVo> pieStatVos = new ArrayList<>();
        for (Object[] monthStatistic : monthStatistics) {
            PieStatVo pieStatVo = new PieStatVo();
            pieStatVo.setName((String) monthStatistic[0]);
            pieStatVo.setValue((BigInteger) monthStatistic[1]);
            pieStatVos.add(pieStatVo);
        }
        System.out.println(pieStatVos);
        return pieStatVos;
    }
}
