package com.example.springbootdemo.entity;

import javax.persistence.*;
import java.math.BigDecimal;


/**
 * @author: zhuguannan
 * @date: 2024-06-29 18:58:45
 * @class: Book
 * @description: 图书信息
 */
@Table(name = "book")
public class Book {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 图书名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 图书价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 图书作者
     */
    @Column(name = "author")
    private String author;

    /**
     * 图书出版社
     */
    @Column(name = "press")
    private String press;

    /**
     * 图书封面
     */
    @Column(name = "img")
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
