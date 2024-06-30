package com.example.springbootdemo.vo.type;

import com.example.springbootdemo.vo.common.Page;

/**
 * @author: zhuguannan
 * @date: 2024-06-30 16:41
 * @class: FindTypeBySearchVO
 * @description: 根据参数查询分类信息
 */
public class FindTypeBySearchVO extends Page {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
