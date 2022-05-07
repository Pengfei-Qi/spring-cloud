package com.fei.springcloud.controller;

import com.fei.springcloud.vo.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用于查询远程git配置信息
 * @author: qpf
 * @date: 2022/5/7
 * @version: 1.0
 */
@RestController
@RefreshScope
public class GitController {

    @Autowired
    private GitConfig gitConfig;

    @GetMapping(value = "show")
    public Object show(){
        return gitConfig;
    }

}

