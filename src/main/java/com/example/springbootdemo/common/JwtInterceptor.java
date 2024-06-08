package com.example.springbootdemo.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springbootdemo.entity.Admin;
import com.example.springbootdemo.exception.CustomException;
import com.example.springbootdemo.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zhuguannan
 * @date: 2024-06-09 2:56
 * @class: JwtInterceptor
 * @description: JWT拦截器
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private AdminService adminService;

    /**
     * @param request  请求参数
     * @param response 响应参数
     * @param handler  处理
     * @return boolean
     * @author zhuguannan
     * @date 2024-06-09
     * @description: 验证token
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、从http请求的header中获取token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            //如果没有token，再去参数里面获取
            token = request.getParameter("token");
        }

        //2、开始执行认证
        if (StrUtil.isBlank(token)) {
            throw new CustomException("没有token，请重新登录");
        }

        //获取token中的adminId
        String adminId;

        Admin admin;

        try {
            //解码
            adminId = JWT.decode(token).getAudience().get(0);
            //根据token中的adminId查询数据库
            admin = adminService.findById(Integer.parseInt(adminId));
        } catch (Exception e) {
            String errMsg = "token验证失败，请重新登录";
            log.error("{}，token={}", errMsg, token, e);
            throw new CustomException(errMsg);
        }

        if (admin == null) {
            throw new CustomException("用户不存在，请重新登录");
        }

        try {
            //用户密码加签验证token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
            //验证token
            jwtVerifier.verify(token);
        } catch (Exception e) {
            throw new CustomException("token验证失败，请重新登录");
        }
        return true;
    }
}
