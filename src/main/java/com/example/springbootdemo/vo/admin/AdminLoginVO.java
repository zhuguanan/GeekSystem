package com.example.springbootdemo.vo.admin;

/**
 * @author: zhuguannan
 * @date: 2024-04-21 17:37
 * @class: AdminLoginVO
 * @description: 管理员登录
 */
public class AdminLoginVO {

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
