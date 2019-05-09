package com.example.demo.service.Implements;


import com.example.demo.service.SendMailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendMailServiceImplements implements SendMailService {

    @Resource
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    @Async
    public void sendMailForResetPassWord(String email, String password) {
        try {
            if (email != null && email.length() != 0 && password != null && password.length() != 0) {
                MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                //邮件发送方
                mimeMessageHelper.setFrom(from);
                //邮件主题
                mimeMessageHelper.setSubject("【CoolBlog】");
                //邮件接收方
                mimeMessageHelper.setTo(email);
                //邮件内容
                mimeMessageHelper.setText("已重置密码为:【" + password + "】,请使用此密码登陆系统，更改密码请到个人中心");
                //发送邮件
                this.mailSender.send(mimeMessage);
                logger.info("发送一条重置密码邮件:" + email);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
