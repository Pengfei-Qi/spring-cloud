package com.fei.springcloud.controller;

import com.fei.springcloud.feign.UseCompanyClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/27
 * @version: 1.0
 */
@Import(FeignClientsConfiguration.class)
@RestController
public class BasicAuthFeignController {
    //用户FeignClient
    private UseCompanyClient userFeignClient;
    //管理员Feignclient
    private UseCompanyClient adminFeignClient;

    /**
     * 初始化Controller时创建 根据用户和角色的不同 创建不同形态的 Client
     * 利用BasicHttp 进行认证的方式 传输不同的用户名和密码
     *
     * @param decoder
     * @param encoder
     * @param client
     * @param contract
     */
    public BasicAuthFeignController(Decoder decoder, Encoder encoder, Client client, Contract contract) {

        /**
         * 这里的构造方法的参数 是通过 @Import(FeignClientsConfiguration.class)导入Bean的方式注入进来的
         */
        this.userFeignClient = Feign.builder().client(client).
                encoder(encoder).decoder(decoder).contract(contract).
                requestInterceptor(new BasicAuthRequestInterceptor("xiaoming", "123")).
                target(UseCompanyClient.class, "http://spring-cloud-provider");

        this.adminFeignClient = Feign.builder().client(client).encoder(encoder).
                decoder(decoder).contract(contract).
                requestInterceptor(new BasicAuthRequestInterceptor("zhangsan", "123")).
                target(UseCompanyClient.class, "http://spring-cloud-provider");
    }
    //管理员调用管理员的Client
    @RequestMapping("/admin")
    public String getAdmin(){
        String loginName = adminFeignClient.getLoginName();
        return loginName;
    }
    //普通用户调用普通用户的Client
    @RequestMapping("/user")
    public String getUser(){
        String loginName = userFeignClient.getLoginName();
        return loginName;
    }
}
