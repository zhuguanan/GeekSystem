package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.Result;
import com.example.springbootdemo.entity.Book;
import com.example.springbootdemo.service.BookService;
import com.example.springbootdemo.vo.book.AddBookVO;
import com.example.springbootdemo.vo.book.FindBookBySearchVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: zhuguannan
 * @date: 2024-06-29 19:05:14
 * @class: BookController
 * @description: 图书信息
 */
@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    /**
     * @param request 请求参数
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-06-29
     * @description: 根据查询条件查询图书信息
     */
    @GetMapping("/findBookBySearch")
    public Result findBookBySearch(FindBookBySearchVO request) {
        PageInfo<Book> bookInfo = bookService.findBookBySearch(request);
        return Result.success(bookInfo);
    }

    /**
     * @param request 请求参数
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-06-29
     * @description: 保存管理员信息
     */
    @PostMapping("/saveBook")
    public Result saveBook(@RequestBody AddBookVO request) {
        if (request.getId() == null) {
            //新增
            bookService.addBook(request);
        } else {
            //编辑
            bookService.updateBook(request);
        }
        return Result.success();
    }

    /**
     * @param id 主键id
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-06-29
     * @description: 删除图书信息
     */
    @DeleteMapping("/deleteBook/{id}")
    public Result deleteBookById(@PathVariable Integer id) {
        bookService.deleteBookById(id);
        return Result.success();
    }
}
