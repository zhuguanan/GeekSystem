package com.example.springbootdemo.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.springbootdemo.dao.TypeDao;
import com.example.springbootdemo.entity.Type;
import com.example.springbootdemo.exception.CustomException;
import com.example.springbootdemo.vo.type.AddTypeVO;
import com.example.springbootdemo.vo.type.FindTypeBySearchVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhuguannan
 * @date: 2024-06-30
 * @class: TypeService
 * @description: 图书分类
 */

@Service
public class TypeService {

    @Resource
    private TypeDao typeDao;

    /**
     * @param request 请求参数
     * @return com.github.pagehelper.PageInfo<com.example.springbootdemo.entity.Type>
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 根据查询条件查询分类信息
     */
    public PageInfo<Type> findTypeBySearch(FindTypeBySearchVO request) {
        // 开启分页查询
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        // 接下来的查询货自动按照当前开启的分页设置来查询
        List<Type> typeList = typeDao.findTypeBySearch(request);

        return PageInfo.of(typeList);
    }

    /**
     * @param request 请求参数
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 保存分类信息
     */
    public void addBook(AddTypeVO request) {

        if (StrUtil.isBlank(request.getName())) {
            throw new CustomException("分类名称不能为空");
        }

        //查询分类是否存在
        Type typeInfo = typeDao.getTypeInfoByName(request.getName());
        if (typeInfo != null) {
            //存在
            throw new CustomException("该分类名称已存在，请勿重复添加");
        }
        Type type = new Type();
        type.setName(request.getName());
        type.setDescription(request.getDescription());
        typeDao.addTypeInfo(type);
    }

    /**
     * @param request 请求参数
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 更新分类信息
     */
    public void updateBook(AddTypeVO request) {
        Type type = new Type();
        type.setId(request.getId());
        type.setName(request.getName());
        type.setDescription(request.getDescription());
        //根据主键id更新数据
        typeDao.updateByPrimaryKeySelective(type);
    }

    /**
     * @param id 主键id
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 删除分类信息
     */
    public void deleteTypeById(Integer id) {
        typeDao.deleteByPrimaryKey(id);
    }

    /**
     * @param typeList 分类信息
     * @author zhuguannan
     * @date 2024-06-30
     * @description: 批量删除分类信息
     */
    public void delBatch(List<Type> typeList) {
        for (Type type : typeList) {
            typeDao.deleteByPrimaryKey(type.getId());
        }
    }

    /**
     * @param response 响应消息
     * @author zhuguannan
     * @date 2024-07-27
     * @description: 导出
     */
    public void export(HttpServletResponse response) throws IOException {
        //从数据库中查询出所有数据
        List<Type> typeList = typeDao.selectAll();
        if (CollectionUtil.isEmpty(typeList)) {
            throw new CustomException("没有需要导出的数据！");
        }
        //定义一个List，存储处理之后的数据，用于将数据处理到list中
        List<Map<String, Object>> list = new ArrayList<>();

        //定义一个map<key,value>，遍历每一条数据，封装到map<key,value>，将map处理到list中
        for (Type type : typeList) {
            Map<String, Object> row = new HashMap<>();
            row.put("分类名称", type.getName());
            row.put("分类描述", type.getDescription());
            list.add(row);
        }

        //创建一个ExcelWriter，把list数据用writer写出来（生成出来）
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        //将Excel下载下来
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=type.xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }

}
