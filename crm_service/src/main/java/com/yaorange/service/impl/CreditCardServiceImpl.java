package com.yaorange.service.impl;

import com.yaorange.dao.CreditCardDao;
import com.yaorange.entity.CreditCard;
import com.yaorange.service.CreditCardService;
import com.yaorange.utils.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassNameCardServiceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 21:10
 * @Version 1.0
 **/
@Service("creditCardService")
public class CreditCardServiceImpl implements CreditCardService {
    @Resource(name = "creditCardDao")
    private CreditCardDao creditCardDao;

    @Override
    public void addCard(CreditCard creditCard) {
        creditCardDao.add(creditCard);
    }

    @Override
    public void updateCard(CreditCard creditCard) {
        creditCardDao.update(creditCard);
    }

    @Override
    public Pagination page(Integer id, Integer current, Integer pageSize) {
        Pagination pagination = creditCardDao.getPage("from CreditCard where customerId=?1", current, pageSize, id);
        return pagination;
    }

    @Override
    public void deleteCard(Integer[] ids) {
        for (Integer id : ids) {
            creditCardDao.delete(id);
        }
    }
}
