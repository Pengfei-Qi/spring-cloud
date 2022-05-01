package com.fei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/17
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
//开启全局的熔断器,支持服务降级
@EnableCircuitBreaker
public class ProviderApplication_8002 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication_8002.class,args);
    }
}
