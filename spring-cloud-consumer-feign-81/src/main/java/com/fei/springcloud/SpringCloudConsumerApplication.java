package com.fei.springcloud;

import com.fei.customize.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/16
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
// @EnableFeignClients
//开启全局的Feign配置
@EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
public class SpringCloudConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerApplication.class,args);
    }
}
