package com.example.springbootdemo.exception;

/**
 * @author: zhuguannan
 * @date: 2024-04-21 18:31
 * @class: CustomException
 * @description: 自定义异常
 */
public class CustomException extends RuntimeException {

    private String msg;

    public CustomException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
