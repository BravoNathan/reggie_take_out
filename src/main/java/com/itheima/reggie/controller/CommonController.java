package com.itheima.reggie.controller;

import com.itheima.reggie.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/19
 */
@RestController
@Slf4j
@RequestMapping("/common")
@Api(tags = "文件传输功能")
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info(file.toString());
        // 原始文件名
        String originalFilename = file.getOriginalFilename();
        // 截取出文件
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 使用UUID重新生成文件名防止文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;

        // 创建目录对象
        File dir = new File(this.basePath);
        if(!dir.exists()){
            // 如果目录不存在，创建目录
            dir.mkdir();
        }

        try{
            // 将目录输出到指定的位置
            file.transferTo(new File(this.basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.success(fileName);
    }

    @ApiOperation(value = "文件下载")
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try {
            // 读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(this.basePath + name));
            // 输出流，用于写入浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();
            int len = 0;
            byte[] bytes = new byte[1024];
            while((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
