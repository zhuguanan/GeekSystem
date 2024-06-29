package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.Book;
import com.example.springbootdemo.vo.book.FindBookBySearchVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: zhuguannan
 * @date: 2024-06-29 18:58:45
 * @class: BookDao
 * @description: 图书信息服务
 */
@Repository
public interface BookDao extends Mapper<Book> {

    /**
     * @param request 请求参数
     * @return java.util.List<com.example.springbootdemo.entity.Book>
     * @author zhuguannan
     * @date 2024-06-29
     * @description: 根据查询条件查询图书信息
     */
    List<Book> findBookBySearch(@Param("request") FindBookBySearchVO request);


    Book getBookInfoByName(@Param("name") String name);

    void addBookInfo(@Param("request") Book book);

}
