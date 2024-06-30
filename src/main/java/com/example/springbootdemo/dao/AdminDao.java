package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.Admin;
import com.example.springbootdemo.vo.admin.AdminLoginVO;
import com.example.springbootdemo.vo.admin.FindAdminBySearchVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: zhuguannan
 * @date: 2024-04-11 23:12
 * @class: UserDao
 * @description: 管理员服务
 */
@Repository
public interface AdminDao extends Mapper<Admin> {

    /**
     * @return java.util.List<com.example.springbootdemo.entity.User>
     * @author zhuguannan
     * @date 2024-04-12
     * @description: 获取所有管理员信息
     */
    List<Admin> getAdminList();

    /**
     * @param request 请求参数
     * @return java.util.List<com.example.springbootdemo.entity.Admin>
     * @author zhuguannan
     * @date 2024-04-14
     * @description: 根据查询条件查询管理员信息
     */
    List<Admin> findAdminBySearch(@Param("request") FindAdminBySearchVO request);

    /**
     * @param name 用户名
     * @return com.example.springbootdemo.entity.Admin
     * @author zhuguannan
     * @date 2024-04-21
     * @description: 根据用户名查询管理员是否存在
     */
    Admin getAdminInfoByName(@Param("name") String name);

    /**
     * @param request 请求参数
     * @return com.example.springbootdemo.entity.Admin
     * @author zhuguannan
     * @date 2024-04-21
     * @description: 根据用户名和密码查询管理员是否存在
     */
    Admin getAdminInfoByNameAndPassword(@Param("request") AdminLoginVO request);

    /**
     * @param admin 新增实体
     * @author zhuguannan
     * @date 2024-04-21
     * @description: 新增管理员信息
     */
    void addAdminInfo(@Param("request") Admin admin);
}
