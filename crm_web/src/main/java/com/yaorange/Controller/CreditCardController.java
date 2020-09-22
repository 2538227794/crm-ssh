package com.yaorange.Controller;

import com.yaorange.entity.CreditCard;
import com.yaorange.service.CreditCardService;
import com.yaorange.utils.Pagination;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassNameCardController
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 21:08
 * @Version 1.0
 **/
@RestController
@RequestMapping("/card")
public class CreditCardController {
    @Resource(name = "creditCardService")
    private CreditCardService creditCardService;

    @PostMapping("/deleteCard")
    public String deleteCard(@RequestBody Integer[] ids) {
        creditCardService.deleteCard(ids);
        return "ok";
    }

    @GetMapping("/page/{id}/{current}/{pageSize}")
    public Pagination page(@PathVariable Integer id, @PathVariable Integer current, @PathVariable Integer pageSize) {
        Pagination pagination = creditCardService.page(id, current, pageSize);
        return pagination;
    }


    @PostMapping("/addCard")
    public String addCard(@RequestBody CreditCard creditCard) {
        creditCardService.addCard(creditCard);
        return "ok";
    }

    @PostMapping("/updateCard")
    public String updateCard(@RequestBody CreditCard creditCard) {
        creditCardService.updateCard(creditCard);
        return "ok";
    }
}
