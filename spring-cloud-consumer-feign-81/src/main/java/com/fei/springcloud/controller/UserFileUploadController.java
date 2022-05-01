package com.fei.springcloud.controller;

import com.fei.springcloud.common.dto.ResponseData;
import com.fei.springcloud.common.enums.ErrorCodeEnum;
import com.fei.springcloud.feign.UserFileUploadClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:  实现文件上传功能
 * @author: qpf
 * @date: 2022/5/1
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/file")
public class UserFileUploadController {

    @Autowired
    private UserFileUploadClient feignUserService;

    @RequestMapping(value = "/fileUploadTest",method = RequestMethod.POST)
    public ResponseData<?> fileUploadTest(@RequestPart(value = "file") MultipartFile file, String remark){
        log.info("开始文件上传");
        if(file == null || file.getSize()<=0){
            log.info("文件大小不满足要求");
            return ResponseData.response(ErrorCodeEnum.FAIL,null);
        }
        ResponseData<?> responseData = feignUserService.fileUploadTest(file,remark);
        log.info("文件上传结束");
        return responseData;
    }
}
