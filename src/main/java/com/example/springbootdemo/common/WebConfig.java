package com.example.springbootdemo.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author: zhuguannan
 * @date: 2024-05-13 23:43
 * @class: WebConfig
 * @description: 接口加上统一前缀，配置一个拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    /**
     * @param configurer 配置
     * @author zhuguannan
     * @date 2024-06-09
     * @description: 增加前缀
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //指定controller统一的接口前缀，相当于在url上拼接了一个/api前缀
        configurer.addPathPrefix("/api", clazz -> clazz.isAnnotationPresent(RestController.class));
    }

    /**
     * @param registry 注册
     * @author zhuguannan
     * @date 2024-06-09
     * @description: 加载自定义拦截器JwtInterceptor，设置拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**")//验证api下的所有页面
                .excludePathPatterns("/api/admin/login")//排除登录
                .excludePathPatterns("/api/admin/register");//排除注册
    }
}
