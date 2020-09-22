package com.yaorange.service;

import com.yaorange.entity.CreditCard;
import com.yaorange.utils.Pagination;

/**
 * @ClassNameCardService
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 21:09
 * @Version 1.0
 **/
public interface CreditCardService {
    void addCard(CreditCard creditCard);

    void updateCard(CreditCard creditCard);

    Pagination page(Integer id, Integer current, Integer pageSize);

    void deleteCard(Integer[] ids);
}
