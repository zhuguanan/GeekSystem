package com.example.springbootdemo.common;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author: zhuguannan
 * @date: 2024-06-09 4:16
 * @class: CorsConfig
 * @description: 设置自定义头
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        //1、设置访问源地址
        config.addAllowedOrigin("*");
        //2、设置访问源请求头
        config.addAllowedHeader("*");
        //3、设置访问源请求方法
        config.addAllowedMethod("*");
        //4、对接口配置跨域设置
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
