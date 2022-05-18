package com.fei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @description: 测试zuul网关
 * @author: qpf
 * @date: 2022/5/9
 * @version: 1.0
 */
@EnableZuulProxy
@SpringCloudApplication
public class GatewayZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulApplication.class, args);
    }
}
