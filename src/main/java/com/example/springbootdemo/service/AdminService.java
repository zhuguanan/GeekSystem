package com.example.springbootdemo.service;

import com.example.springbootdemo.common.JwtTokenUtils;
import com.example.springbootdemo.dao.AdminDao;
import com.example.springbootdemo.entity.Admin;
import com.example.springbootdemo.exception.CustomException;
import com.example.springbootdemo.vo.admin.AddAdminVO;
import com.example.springbootdemo.vo.admin.AdminLoginRespVO;
import com.example.springbootdemo.vo.admin.AdminLoginVO;
import com.example.springbootdemo.vo.admin.FindAdminBySearchVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author: zhuguannan
 * @date: 2024-04-11 23:08
 * @class: UserService
 * @description: 管理员信息
 */

@Service
public class AdminService {

    @Resource
    private AdminDao adminDao;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    /**
     * @return java.util.List<com.example.springbootdemo.entity.User>
     * @author zhuguannan
     * @date 2024-04-12
     * @description: 获取所有管理员信息
     */
    public List<Admin> getAdminList() {
        return adminDao.getAdminList();
    }

    /**
     * @param request 请求参数
     * @return java.util.List<com.example.springbootdemo.entity.Admin>
     * @author zhuguannan
     * @date 2024-04-14
     * @description: 根据查询条件查询管理员信息
     */
    public PageInfo<Admin> findAdminBySearch(FindAdminBySearchVO request) {
        // 开启分页查询
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        // 接下来的查询货自动按照当前开启的分页设置来查询
        List<Admin> adminList = adminDao.findAdminBySearch(request);

        return PageInfo.of(adminList);
    }

    /**
     * @param request 请求参数
     * @author zhuguannan
     * @date 2024-04-14
     * @description: 新增管理员信息
     */
    public void addAdmin(AddAdminVO request) {

        if (request.getName() == null || Objects.equals(request.getName(), "")) {
            throw new CustomException("用户名不能为空");
        }

        if (request.getPassword() == null || Objects.equals(request.getPassword(), "")) {
            request.setPassword(generateRandomPassword());
        }
        //查询管理员是否存在
        Admin adminInfo = adminDao.getAdminInfoByName(request.getName());
        if (adminInfo != null) {
            //存在
            throw new CustomException("改用户名已存在，请勿重复添加");
        }
        Admin admin = new Admin();
        admin.setName(request.getName());
        admin.setPassword(request.getPassword());
        admin.setSex(request.getSex());
        admin.setPhone(request.getPhone());
        admin.setAge(request.getAge());
        adminDao.addAdminInfo(admin);
    }

    /**
     * @return java.lang.String
     * @author zhuguannan
     * @date 2024-04-14
     * @description: 随机生成6位数字密码
     */
    public static String generateRandomPassword() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000; // 生成100000到999999之间的随机数
        return String.valueOf(randomNumber);
    }

    /**
     * @param request 请求参数
     * @author zhuguannan
     * @date 2024-04-14
     * @description: 编辑管理员信息
     */
    public void updateAdmin(AddAdminVO request) {
        Admin admin = new Admin();
        admin.setId(request.getId());
        admin.setName(request.getName());
        admin.setPassword(request.getPassword());
        admin.setSex(request.getSex());
        admin.setPhone(request.getPhone());
        admin.setAge(request.getAge());
        //根据主键id更新数据
        adminDao.updateByPrimaryKeySelective(admin);
    }

    /**
     * @param id 主键id
     * @author zhuguannan
     * @date 2024-04-16
     * @description: 删除管理员信息
     */
    public void deleteAdminById(Integer id) {
        adminDao.deleteByPrimaryKey(id);
    }

    /**
     * @param request 请求参数
     * @return com.example.springbootdemo.vo.admin.AdminLoginRespVO
     * @author zhuguannan
     * @date 2024-04-21
     * @description: 管理员登录
     */
    public AdminLoginRespVO login(AdminLoginVO request) {

        // 1、进行非空校验
        if (request.getName() == null || Objects.equals(request.getName(), "")) {
            throw new CustomException("登录的用户名不能为空");
        }
        if (request.getPassword() == null || Objects.equals(request.getPassword(), "")) {
            throw new CustomException("登录的密码不能为空");
        }

        //查询账号密码是否存在，存在，则用户名和密码正确；不存在，则用户名和密码错误
        Admin adminInfo = adminDao.getAdminInfoByNameAndPassword(request);
        if (adminInfo == null) {
            throw new CustomException("登录的用户名或者密码错误");
        }

        //生成token
        String token = JwtTokenUtils.genToken(adminInfo.getId().toString(), adminInfo.getPassword());

        AdminLoginRespVO adminLoginRespVO = new AdminLoginRespVO();
        adminLoginRespVO.setId(adminInfo.getId());
        adminLoginRespVO.setName(adminInfo.getName());
        adminLoginRespVO.setPassword(adminInfo.getPassword());
        adminLoginRespVO.setAge(adminInfo.getAge());
        adminLoginRespVO.setSex(adminInfo.getSex());
        adminLoginRespVO.setPhone(adminInfo.getPhone());
        adminLoginRespVO.setToken(token);
        return adminLoginRespVO;
    }

    public Admin findById(Integer id) {
        return adminDao.selectByPrimaryKey(id);
    }
}
