package com.example.demo.entity.user;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户账号表,将用户的账号信息与用户信息分离
 * 2019.3.7
 *
 * @author tiga
 * @version 1.0
 */
@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp registerTime;

    private Date lastLoginTime;

    public UserAccount() {

    }

    public UserAccount(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "[账号: 用户Id:" + userId + ", 账号:" + account + ", 注册时间:" + registerTime + ", 最后登录时间:" + lastLoginTime + "]";
    }

}
