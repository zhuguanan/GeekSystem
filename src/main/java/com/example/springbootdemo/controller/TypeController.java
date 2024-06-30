package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.Result;
import com.example.springbootdemo.entity.Type;
import com.example.springbootdemo.service.TypeService;
import com.example.springbootdemo.vo.type.AddTypeVO;
import com.example.springbootdemo.vo.type.FindTypeBySearchVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zhuguannan
 * @date: 2024-06-30
 * @class: TypeController
 * @description: 图书信息
 */
@CrossOrigin
@RestController
@RequestMapping("/type")
public class TypeController {

    @Resource
    private TypeService typeService;

    /**
     * @param request 请求参数
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 根据查询条件查询分类信息
     */
    @GetMapping("/findTypeBySearch")
    public Result findTypeBySearch(FindTypeBySearchVO request) {
        PageInfo<Type> typeInfo = typeService.findTypeBySearch(request);
        return Result.success(typeInfo);
    }

    /**
     * @param request 请求参数
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 保存分类信息
     */
    @PostMapping("/saveType")
    public Result saveBook(@RequestBody AddTypeVO request) {
        if (request.getId() == null) {
            //新增
            typeService.addBook(request);
        } else {
            //编辑
            typeService.updateBook(request);
        }
        return Result.success();
    }

    /**
     * @param id 主键id
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 删除分类信息
     */
    @DeleteMapping("/deleteType/{id}")
    public Result deleteTypeById(@PathVariable Integer id) {
        typeService.deleteTypeById(id);
        return Result.success();
    }

    /**
     * @param typeList 分类信息
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 批量删除分类信息
     */
    @PutMapping("/delBatch")
    public Result delBatch(@RequestBody List<Type> typeList) {
        typeService.delBatch(typeList);
        return Result.success();
    }
}
