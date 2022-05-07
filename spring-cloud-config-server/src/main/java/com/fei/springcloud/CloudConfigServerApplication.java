package com.fei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description: spring cloud config server 启动类
 * @author: qpf
 * @date: 2022/5/7
 * @version: 1.0
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class CloudConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServerApplication.class, args);
    }
}
