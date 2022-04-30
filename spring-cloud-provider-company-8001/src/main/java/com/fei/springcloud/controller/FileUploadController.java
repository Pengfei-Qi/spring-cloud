package com.fei.springcloud.controller;



import com.fei.springcloud.common.dto.ResponseData;
import com.fei.springcloud.common.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @description: 上传文件的接口，写法就是SpringMVC的上传接口的写法
 * @author: qpf
 * @date: 2022/5/1
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class FileUploadController {

    @RequestMapping(value = "/fileUploadTest",method = RequestMethod.POST)
    public ResponseData<?> fileUploadTest(@RequestParam(value = "file") MultipartFile file, String remark){
        log.info("开始文件上传,remakr={}",remark);
        if(file == null || file.getSize()<=0){
            log.info("文件大小不满足要求");
            return ResponseData.response(ErrorCodeEnum.FAIL,null);
        }
        try {
            file.transferTo(new File("d://"+file.getOriginalFilename()));
        } catch (IOException e) {
            log.error("文件上传失败",e);
            return ResponseData.response(ErrorCodeEnum.FAIL,null);
        }
        log.info("文件上传结束");
        return ResponseData.response(ErrorCodeEnum.SUCCESS,null);
    }
}
