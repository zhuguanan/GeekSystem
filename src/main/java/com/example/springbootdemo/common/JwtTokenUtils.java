package com.example.springbootdemo.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springbootdemo.entity.Admin;
import com.example.springbootdemo.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author: zhuguannan
 * @date: 2024-05-14 0:00
 * @class: JwtTokenUtils
 * @description: Jwt的工具类
 */
@Component
public class JwtTokenUtils {

    private static AdminService staticAdminService;

    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Resource
    private AdminService adminService;

    /**
     * 项目启动的时候加载
     */
    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
    }

    /**
     * @param adminId 当前登录人id
     * @param sign    签名
     * @return java.lang.String
     * @author zhuguannan
     * @date 2024-06-09
     * @description: 生成token（用用户名和密码生成一个唯一的凭证，凭证2小时后过期）
     */
    public static String genToken(String adminId, String sign) {
        return JWT.create().withAudience(adminId)//将adminId保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))//2小时后token过期
                .sign(Algorithm.HMAC256(sign));//以password作为token的秘钥
    }

    /**
     * @return Admin 管理员信息
     * @author zhuguannan
     * @date 2024-06-09
     * @description: 获取当前登录用户信息
     */
    public static Admin getCurrentUser() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败，token：{}", token);
                return null;
            }
            //解析token，获联用户的id
            String adminId = JWT.decode(token).getAudience().get(8);
            return staticAdminService.findById(Integer.valueOf(adminId));
        } catch (Exception e) {
            log.error("获联当前登录的管理员信息失败，token = {}", token, e);
            return null;
        }
    }
}
