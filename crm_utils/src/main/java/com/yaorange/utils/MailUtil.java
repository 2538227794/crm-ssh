package com.yaorange.utils;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.*;

/**
 * @ClassNameutils
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/14 16:22
 * @Version 1.0
 **/
@Component("mailUtil")
public class MailUtil {
    @Resource(name = "mailMessage")
    private SimpleMailMessage mailMessage;
    @Resource(name = "mailSender")
    private JavaMailSender mailSender;

    public void sendMail(String target, String title, String text) throws MessagingException {
        //设置收件人
        mailMessage.setTo(target);
        //设置标题
        mailMessage.setSubject(title);
        //设置正文
        mailMessage.setText(text);
        //发送邮件
        mailSender.send(mailMessage);
    }
}
