package com.example.springbootdemo.vo.common;

/**
 * @author: zhuguannan
 * @date: 2024-04-14 1:00
 * @class: Page
 * @description: 分页
 */
public class Page {
    /**
     * 第几页
     */
    private Integer pageNum;

    /**
     * 每页多少条
     */
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
