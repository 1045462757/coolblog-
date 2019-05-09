package com.example.demo.service;

public interface SendMailService {

    /**
     * 发送重置密码邮件
     *
     * @param email    邮箱
     * @param password 新密码
     */
    void sendMailForResetPassWord(String email, String password);
}
