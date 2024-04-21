package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhuguannan
 * @date: 2024-04-10 23:06
 * @class: TestController
 * @description: 测试
 */
@RestController
@RequestMapping("/test")
public class TestController {
    /**
     * @return java.lang.String
     * @author zhuguannan
     * @date 2024-04-10
     * @description: 第一个测试方法
     */
    @GetMapping("/say/hello")
    public Result sayHello() {
        return Result.success("hello spring boot");
    }
}
