package com.fei.springcloud.controller;

import com.fei.springcloud.feign.BasicAuthClient;
import com.fei.springcloud.pojo.CompanyTbl;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 *                  1. 基于feign的认证方式调用接口，认证方式为basicAuth
 *                  2. 请求方式为restTemplate调用消费端接口,因此不需要 :
 *                          a. 主启动类的 @EnableFeignClients 注解
 *                          b. BasicAuthClient.class 也不需要 @FeignClient("spring-cloud-provider")
 *                  3.  参考链接1: https://www.jianshu.com/p/755b15ff0249
 *                  4.  参考链接2:
 *                  http://thread-blog.org/2021/08/25/SpringCloud/SpringCloud-%E4%BD%BF%E7%94%A8Feign%E5%AE%9E%E7%8E%B0%E5%A3%B0%E6%98%8E%E5%BC%8FREST%E8%B0%83%E7%94%A8/
 * @author: qpf
 * @date: 2022/4/27
 * @version: 1.0
 */
@Import(FeignClientsConfiguration.class)
@RestController
@RequestMapping("/basicAuth")
public class BasicAuthFeignController {
    //用户FeignClient
    // private BasicAuthClient userFeignClient;
    //管理员Feignclient
    private BasicAuthClient adminFeignClient;

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
        // this.userFeignClient = Feign.builder().client(client).
        //         encoder(encoder).decoder(decoder).contract(contract).
        //         requestInterceptor(new BasicAuthRequestInterceptor("xiaoming", "123")).
        //         target(BasicAuthClient.class, "http://SPRING-CLOUD-PROVIDER");

        this.adminFeignClient = Feign.builder().client(client).encoder(encoder).
                decoder(decoder).contract(contract).
                requestInterceptor(new BasicAuthRequestInterceptor("zhangsan", "123")).
                target(BasicAuthClient.class, "http://SPRING-CLOUD-PROVIDER");
    }
    //管理员调用管理员的Client
    @RequestMapping("/admin")
    public String getAdmin(){
        String loginName = adminFeignClient.getLoginName();
        return loginName;
    }
    //普通用户调用普通用户的Client
    // @RequestMapping("/user")
    // public String getUser(){
    //     String loginName = userFeignClient.getLoginName();
    //     return loginName;
    // }

    @GetMapping("/cloud/get/{id}")
    public CompanyTbl getCompleteById(@PathVariable("id") int id){
        return this.adminFeignClient.selectById(id);
    }

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll(){
        return this.adminFeignClient.getAll();
    }

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl){
        return this.adminFeignClient.addCompany(companyTbl);
    }

    @GetMapping("/cloud/delete/{id}")
    public Map deleteCompany(@PathVariable("id") int id){
        return this.adminFeignClient.deleteCompany(id);
    }

    @GetMapping("/loginName")
    public String getLoginName(){
        return this.adminFeignClient.getLoginName();
    }
}
