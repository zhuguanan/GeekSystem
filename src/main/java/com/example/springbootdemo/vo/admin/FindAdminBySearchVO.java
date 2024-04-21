package com.example.springbootdemo.vo.admin;

import com.example.springbootdemo.vo.common.Page;

/**
 * @author: zhuguannan
 * @date: 2024-04-13 23:56
 * @class: FindAdminBySearchVO
 * @description: 根据参数查询管理员信息
 */
public class FindAdminBySearchVO extends Page {

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
