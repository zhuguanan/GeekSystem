package com.example.springbootdemo.common;

/**
 * @author: zhuguannan
 * @date: 2024-04-13 17:44
 * @class: Result
 * @description: 统一返回结果
 */
public class Result {
    /**
     * 成功响应编号
     */
    private static final String SUCCESS = "0";

    /**
     * 失败响应编号
     */
    private static final String ERROR = "-1";

    /**
     * 响应编码
     */
    private String code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-04-13
     * @description: 无返回数据响应成功
     */
    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setMsg("请求成功");
        return result;
    }

    /**
     * @param data 返回数据
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-04-13
     * @description: 有返回数据响应成功
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setMsg("请求成功");
        result.setData(data);
        return result;
    }

    /**
     * @param msg 失败信息
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-04-13
     * @description: 响应失败
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ERROR);
        result.setMsg(msg);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
