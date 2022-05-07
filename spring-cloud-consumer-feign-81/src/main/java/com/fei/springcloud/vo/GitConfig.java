package com.fei.springcloud.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description: 映射 config 目录下的配置字段
 *                  例如: spring-cloud-consumer-dev.yml 里面的内容
 * @author: qpf
 * @date: 2022/5/7
 * @version: 1.0
 */

@Data
@Component
@PropertySource(encoding = "UTF-8", value = {"classpath:application.yml"})
public class GitConfig {

    @Value("${data.env}")
    private String env;

    @Value("${data.user.username}")
    private String username;

    @Value("${data.user.password}")
    private String password;

    @Value("${data.user.tips}")
    private String tips;

}

