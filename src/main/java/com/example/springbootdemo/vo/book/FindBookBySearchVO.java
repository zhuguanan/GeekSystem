package com.example.springbootdemo.vo.book;

import com.example.springbootdemo.vo.common.Page;

/**
 * @author: zhuguannan
 * @date: 2024-06-29 19:11:08
 * @class: FindBookBySearchVO
 * @description: 根据参数查询图书信息
 */
public class FindBookBySearchVO extends Page {

    /**
     * 图书名称
     */
    private String name;

    /**
     * 图书作者
     */
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
