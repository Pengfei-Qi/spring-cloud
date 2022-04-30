package com.fei.springcloud.controller;

import com.fei.springcloud.feign.UseCompanyClient;
import com.fei.springcloud.pojo.CompanyTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/loginName")
    public String getLoginName(){
        return useCompanyClient.getLoginName();
    }
}
