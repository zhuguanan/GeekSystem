package com.example.springbootdemo.vo.book;

import java.math.BigDecimal;

/**
 * @author: zhuguannan
 * @date: 2024-06-29 19:38:04
 * @class: AddBookVO
 * @description: 新增图书信息请求实体
 */
public class AddBookVO {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 图书名称
     */
    private String name;

    /**
     * 图书作者
     */
    private String author;

    /**
     * 图书价格
     */
    private BigDecimal price;

    /**
     * 图书出版社
     */
    private String press;

    /**
     * 图书封面
     */
    private String img;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
