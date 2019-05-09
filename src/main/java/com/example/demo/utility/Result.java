package com.example.demo.utility;

import java.io.Serializable;

/**
 * 业务执行结果
 * 2019.3.4
 *
 * @author tiga
 * @version 1.0
 */
public class Result implements Serializable {

    public static Integer success = 200;

    public static Integer failed = 500;

    //状态，成功:200 失败:500
    private Integer status;
    //失败信息提示
    private String message;
    //数据
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Result(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "[业务处理结果: 状态:" + status + ", 相关信息:" + message + ", 数据:" + data + "]";
    }
}
