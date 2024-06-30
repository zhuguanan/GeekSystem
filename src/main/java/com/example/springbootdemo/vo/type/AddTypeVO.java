package com.example.springbootdemo.vo.type;

/**
 * @author: zhuguannan
 * @date: 2024-06-30 16:55
 * @class: AddTypeVO
 * @description: 新增分类信息请求实体
 */
public class AddTypeVO {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
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
