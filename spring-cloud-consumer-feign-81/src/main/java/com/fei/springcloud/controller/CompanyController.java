package com.fei.springcloud.controller;

import com.fei.springcloud.feign.UseCompanyClient;
import com.fei.springcloud.pojo.CompanyTbl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @description:
 *                 1. 不基于BasicAuth的认证方式
 *  *              2. 使用 feign 调用 spring-cloud-provider 服务提供者的 Restful 接口
 * @author: qpf
 * @date: 2022/4/16
 * @version: 1.0
 */
@RestController
@RequestMapping("/consumer")
public class CompanyController {

    @Autowired
    UseCompanyClient useCompanyClient;

    @GetMapping("/cloud/get/{id}")
    public CompanyTbl getCompleteById(@PathVariable("id") int id){
        return useCompanyClient.selectById(id);
    }

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll(){
        return useCompanyClient.getAll();
    }

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl){
        return useCompanyClient.addCompany(companyTbl);
    }

    @GetMapping("/cloud/delete/{id}")
    public Map deleteCompany(@PathVariable("id") int id){
        return useCompanyClient.deleteCompany(id);
    }

    /**
     * 测试熔断机制
     * @return
     */
    @GetMapping("/testHystrix/all")
    @HystrixCommand(fallbackMethod = "apiFallback")
    public List<CompanyTbl> testHystrix(){
        int i = 1/0;
        return useCompanyClient.getAll();
    }

    /**
     * 熔断机制的回调方法
     * @return
     */
    public List<CompanyTbl> apiFallback(){
        CompanyTbl companyTbl = new CompanyTbl();
        companyTbl.setId(0);
        companyTbl.setName("testHystrix");
        companyTbl.setAddress("熔断机制");
        companyTbl.setCount(9999999);
        companyTbl.setDbSource("this is error info");
        return Arrays.asList(companyTbl);
    }

}
