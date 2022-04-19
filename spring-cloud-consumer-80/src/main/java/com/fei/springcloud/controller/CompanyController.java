package com.fei.springcloud.controller;

import com.fei.springcloud.pojo.CompanyTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/16
 * @version: 1.0
 */
@RestController
@RequestMapping("/consumer")
public class CompanyController {

    @Autowired
    private RestTemplate restTemplate;

    // private static String RESTEMPLETE_SUBFIX="http://localhost:8002";
    //开启客户端负载均衡时使用
    private static String RESTEMPLETE_SUBFIX="http://SPRING-CLOUD-PROVIDER";

    @GetMapping("/cloud/get/{id}")
    public ResponseEntity<CompanyTbl> getCompleteById(@PathVariable("id") int id){
        return restTemplate.getForEntity(RESTEMPLETE_SUBFIX+"/cloud/one/"+id,CompanyTbl.class);
    }

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll(){
        return restTemplate.getForObject(RESTEMPLETE_SUBFIX+"/cloud/all",List.class);
    }

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl){
        return restTemplate.postForObject(RESTEMPLETE_SUBFIX+"/cloud/insert",companyTbl,Map.class);
    }

    @GetMapping("/cloud/delete/{id}")
    public Map addCompany(@PathVariable("id") int id){
        return restTemplate.postForObject(RESTEMPLETE_SUBFIX+"/cloud/delete/"+id, new HashMap<String, Object>(),Map.class);
    }
}
