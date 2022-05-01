package com.fei.springcloud.feign;

import com.fei.customize.FeignConfiguration;
import com.fei.springcloud.pojo.CompanyTbl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 *              1. 不基于BasicAuth的认证方式
 *              2. 使用 feign 调用 spring-cloud-provider 服务提供者的 Restful 接口
 * @author: qpf
 * @date: 2022/4/19
 * @version: 1.0
 */
// 使用默认配置
// @FeignClient("spring-cloud-provider")
// 使用自定义配置类
@FeignClient(contextId = "useCompanyClient",name = "spring-cloud-provider", configuration = FeignConfiguration.class)
public interface UseCompanyClient {

    @GetMapping("/cloud/one/{id}")
    public CompanyTbl selectById(@PathVariable("id") Integer id);

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll();

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl);

    @PostMapping("/cloud/delete/{id}")
    public Map deleteCompany(@PathVariable("id") int id);

}
