package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.Result;
import com.example.springbootdemo.entity.Admin;
import com.example.springbootdemo.service.AdminService;
import com.example.springbootdemo.vo.admin.*;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zhuguannan
 * @date: 2024-04-11 23:10
 * @class: UserController
 * @description: 管理员信息
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * @param request 请求参数
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-04-21
     * @description: 管理员登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody AdminLoginVO request) {

        AdminLoginRespVO loginAdminInfo = adminService.login(request);
        return Result.success(loginAdminInfo);
    }

    /**
     * @param request 请求参数
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-04-21
     * @description: 管理员注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody AdminRegisterVO request) {

        AddAdminVO addAdminVO = new AddAdminVO();
        addAdminVO.setName(request.getName());
        addAdminVO.setPassword(request.getPassword());
        adminService.addAdmin(addAdminVO);
        return Result.success();
    }

    /**
     * @return java.util.List<com.example.springbootdemo.entity.User>
     * @author zhuguannan
     * @date 2024-04-12
     * @description: 获取管理员信息
     */
    @GetMapping("/getAdminList")
    public Result getAdminList() {
        List<Admin> adminList = adminService.getAdminList();
        return Result.success(adminList);
    }

    /**
     * @param request 请求参数
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-04-14
     * @description: 根据查询条件查询管理员信息
     */
    @GetMapping("/findAdminBySearch")
    public Result findAdminBySearch(FindAdminBySearchVO request) {
        PageInfo<Admin> adminInfo = adminService.findAdminBySearch(request);
        return Result.success(adminInfo);
    }

    /**
     * @param request 请求参数
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-04-14
     * @description: 保存管理员信息
     */
    @PostMapping("/saveAdmin")
    public Result saveAdmin(@RequestBody AddAdminVO request) {
        if (request.getId() == null) {
            //新增
            adminService.addAdmin(request);
        } else {
            //编辑
            adminService.updateAdmin(request);
        }
        return Result.success();
    }

    /**
     * @param id 主键id
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-04-16
     * @description: 删除管理员信息
     */
    @DeleteMapping("/deleteAdmin/{id}")
    public Result deleteAdminById(@PathVariable Integer id) {
        adminService.deleteAdminById(id);
        return Result.success();
    }
}
