package com.example.springbootdemo.service;

import cn.hutool.core.util.StrUtil;
import com.example.springbootdemo.dao.BookDao;
import com.example.springbootdemo.entity.Book;
import com.example.springbootdemo.exception.CustomException;
import com.example.springbootdemo.vo.book.AddBookVO;
import com.example.springbootdemo.vo.book.FindBookBySearchVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zhuguannan
 * @date: 2024-06-29 19:02:26
 * @class: BookService
 * @description: 图书信息
 */

@Service
public class BookService {

    @Resource
    private BookDao bookDao;

    /**
     * @param request 请求参数
     * @return com.github.pagehelper.PageInfo<com.example.springbootdemo.entity.Book>
     * @author zhuguannan
     * @date 2024-06-29
     * @description: 根据查询条件查询图书信息
     */
    public PageInfo<Book> findBookBySearch(FindBookBySearchVO request) {
        // 开启分页查询
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        // 接下来的查询货自动按照当前开启的分页设置来查询
        List<Book> bookList = bookDao.findBookBySearch(request);

        return PageInfo.of(bookList);
    }

    /**
     * @param request 请求参数
     * @author zhuguannan
     * @date 2024-06-29
     * @description: 新增图书信息
     */
    public void addBook(AddBookVO request) {

        if (StrUtil.isBlank(request.getName())) {
            throw new CustomException("图书名称不能为空");
        }

        //查询图书是否存在
        Book bookInfo = bookDao.getBookInfoByName(request.getName());
        if (bookInfo != null) {
            //存在
            throw new CustomException("该图书名称已存在，请勿重复添加");
        }
        Book book = new Book();
        book.setName(request.getName());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());
        book.setPress(request.getPress());
        book.setImg(request.getImg());
        bookDao.addBookInfo(book);
    }

    /**
     * @param request 请求参数
     * @author zhuguannan
     * @date 2024-06-29
     * @description: 更新图书信息
     */
    public void updateBook(AddBookVO request) {
        Book book = new Book();
        book.setId(request.getId());
        book.setName(request.getName());
        book.setPrice(request.getPrice());
        book.setPress(request.getPress());
        book.setAuthor(request.getAuthor());
        book.setImg(request.getImg());
        //根据主键id更新数据
        bookDao.updateByPrimaryKeySelective(book);
    }

    /**
     * @param id 主键id
     * @author zhuguannan
     * @date 2024-06-29
     * @description: 删除图书信息
     */
    public void deleteBookById(Integer id) {
        bookDao.deleteByPrimaryKey(id);
    }

}
