package com.fei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/17
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderApplication_8001 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication_8001.class,args);
    }
}
