package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.Type;
import com.example.springbootdemo.vo.type.FindTypeBySearchVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: zhuguannan
 * @date: 2024-06-30
 * @class: TypeDao
 * @description: 图书分类
 */
@Repository
public interface TypeDao extends Mapper<Type> {

    /**
     * @param request 请求参数
     * @return java.util.List<com.example.springbootdemo.entity.Type>
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 根据查询条件查询分类信息
     */
    List<Type> findTypeBySearch(@Param("request") FindTypeBySearchVO request);

    /**
     * @param name 分类名称
     * @return com.example.springbootdemo.entity.Book
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 根据分类名称查询图书信息是否存在
     */
    Type getTypeInfoByName(@Param("name") String name);

    /**
     * @param type 新增实体
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 新增分类信息
     */
    void addTypeInfo(@Param("request") Type type);
}
