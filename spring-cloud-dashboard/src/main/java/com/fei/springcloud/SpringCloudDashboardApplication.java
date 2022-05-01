package com.fei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @description:
 * @author: qpf
 * @date: 2022/5/1
 * @version: 1.0
 */

// 开启Turbine,访问集群的统计信息
@EnableTurbine
// 开启全局的Hystrix监控,以及控制台
@EnableHystrixDashboard
@SpringBootApplication
public class SpringCloudDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDashboardApplication.class, args);
    }
}
