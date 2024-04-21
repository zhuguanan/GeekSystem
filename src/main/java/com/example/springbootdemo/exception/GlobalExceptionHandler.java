package com.example.springbootdemo.exception;

import com.example.springbootdemo.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhuguannan
 * @date: 2024-04-21 18:18
 * @class: GlobalExceptionHandler
 * @description: 全局异常处理器
 */
@ControllerAdvice(basePackages = "com.example.springbootdemo.controller")
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @param request 请求参数
     * @param e       异常信息
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-04-21
     * @description: 统一异常处理，@ExceptionHandler，主要用于Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, Exception e) {
        log.error("异常信息：", e);
        return Result.error("系统异常");
    }

    /**
     * @param request 请求参数
     * @param e       异常信息
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-04-21
     * @description: 自定义异常
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Result customError(HttpServletRequest request, CustomException e) {
        return Result.error(e.getMsg());
    }
}


























