package com.fei.springcloud.config;

import com.fei.customeize.RibbonConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RibbonClient(name = "thread-produce",configuration = RibbonConfiguration.class)
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate myRestTemplate(){
        return new RestTemplate();
    }
}
