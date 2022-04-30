package com.fei.springcloud.feign;

import com.fei.springcloud.pojo.CompanyTbl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: feign基于BasicAuth的客户端
 * @author: qpf
 * @date: 2022/4/30
 * @version: 1.0
 */
public interface BasicAuthClient {

    @GetMapping("/cloud/one/{id}")
    public CompanyTbl selectById(@PathVariable("id") Integer id);

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll();

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl);

    @PostMapping("/cloud/delete/{id}")
    public Map deleteCompany(@PathVariable("id") int id);

    @RequestMapping("/loginName")
    public String getLoginName();
}
