package com.fei.springcloud.controller;

import com.fei.springcloud.pojo.CompanyTbl;
import com.fei.springcloud.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/17
 * @version: 1.0
 */
@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/cloud/one/{id}")
    public CompanyTbl selectById(@PathVariable("id") Integer id){
        return companyService.selectOneById(id);
    }

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll(){
        return companyService.selectAll();
    }

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl){
        Map<String, Object> map = new HashMap<>();
        int count = companyService.insert(companyTbl);
        map.put("msg","success");
        map.put("count",count);
        return map;
    }

    @PostMapping("/cloud/delete/{id}")
    public Map deleteCompany(@PathVariable("id") int id){
        Map<String, Object> map = new HashMap<>();
        int count = companyService.delete(id);
        map.put("msg","success");
        map.put("count",count);
        return map;
    }
}
