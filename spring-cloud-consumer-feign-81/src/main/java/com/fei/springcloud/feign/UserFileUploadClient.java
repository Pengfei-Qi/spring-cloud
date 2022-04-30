package com.fei.springcloud.feign;

import com.fei.customize.FeignConfiguration;
import com.fei.springcloud.common.dto.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:  文件上传
 * @author: qpf
 * @date: 2022/5/1
 * @version: 1.0
 */
@FeignClient(contextId = "userFileClient",name = "spring-cloud-provider", configuration = FeignConfiguration.class)
public interface UserFileUploadClient {

    @RequestMapping(value = "/user/fileUploadTest",
            method= RequestMethod.POST,
            consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseData<?> fileUploadTest(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "remark") String remark);
}
