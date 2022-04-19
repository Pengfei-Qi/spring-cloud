package com.fei.customize;

import feign.Contract;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;

/**
 * @description: 自定义Feign配置信息
 * @author: qpf
 * @date: 2022/4/19
 * @version: 1.0
 */
public class FeignConfiguration {
    /**
     * Feign 默认的契约是 Spring MVC的注解  用的是 SpringMvcContract 所以 FeignClient 可以使用mvc注解来去定义
     * Contract.Default() 改成了 Feign默认的契约 所以这里配置了以后就要修改FeignClient的注解
     * @return
     */
    @Bean
    public Contract feignContract(){
        // return new Contract.Default();
       return new SpringMvcContract();
    }
}
