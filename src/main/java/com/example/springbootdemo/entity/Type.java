package com.example.springbootdemo.entity;

import javax.persistence.*;

/**
 * @author: zhuguannan
 * @date: 2024-06-30
 * @class: Type
 * @description: 图书分类
 */
@Table(name = "type")
public class Type {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 分类名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 分类描述
     */
    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
