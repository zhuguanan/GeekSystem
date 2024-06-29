package com.example.springbootdemo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springbootdemo.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author: zhuguannan
 * @date: 2024-06-29 20:23
 * @class: FileController
 * @description: 文件上传
 */
@RestController
@RequestMapping("/files")
public class FileController {

    //文件上传存储路径
    private static final String filePath = System.getProperty("user.dir") + "/file/";

    /**
     * @param file 需要上传的文件
     * @return com.example.springbootdemo.common.Result
     * @author zhuguannan
     * @date 2024-06-29
     * @description: 文件上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        //文件同步锁（防止文件上传冲突）
        synchronized (FileController.class) {
            //获取当前时间戳
            String flag = System.currentTimeMillis() + "";
            //获取原始文件名（就是你上传的文件本身的名字）
            String fileName = file.getOriginalFilename();
            try {
                //如果没有file文件夹，会在项目根目录下创建一个file文件夹
                if (!FileUtil.isDirectory(filePath)) {
                    FileUtil.mkdir(filePath);
                }
                //文件存储形式：时间戳+文件名
                FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
                System.out.println(fileName + "--文件上传成功！");
                Thread.sleep(1L);
            } catch (Exception e) {
                System.err.println(fileName + "--文件上传失败！");
            }
            return Result.success(flag);
        }
    }

    /**
     * @param flag     时间戳
     * @param response 返回流
     * @author zhuguannan
     * @date 2024-06-29
     * @description: 获取文件
     */
    @GetMapping("/{flag}")
    public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
        if (!FileUtil.isDirectory(filePath)) {
            FileUtil.mkdir(filePath);
        }
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(filePath);
        String avatar = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(avatar)) {
                response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(avatar, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + avatar);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }

        } catch (Exception e) {
            System.out.println("文件下载失败！");
        }
    }
}
