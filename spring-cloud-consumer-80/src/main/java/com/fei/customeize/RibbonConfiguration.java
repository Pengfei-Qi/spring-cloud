package com.fei.customeize;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 自定义 Ribbon配置
 * @author: qpf
 * @date: 2022/4/18
 * @version: 1.0
 */
@Configuration
public class RibbonConfiguration {
    /**
     * 选择一个并发最小的进行分配
     * @return
     */
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
//        return new PingUrl();
    }

    /**
     * IPing机制
     * 用来判断服务节点存活的一种机制，pringUrl 是发一次 http请求来判断是否存活
     * @return
     */
    @Bean
    public IPing ribbonPing(){
        return new DummyPing();
    }

    /**
     * 以下两种需要时再做了解
     * @return
     */
    /*
    @Bean
    public ServerListSubsetFilter serverListSubsetFilter(){
        ServerListSubsetFilter filter = new ServerListSubsetFilter();
        return filter;
    }

    @Bean
    public ServerList<Server> ribbonServerList(IClientConfig config){
        return new BazServiceList(config);
    }

    public static class BazServiceList extends ConfigurationBasedServerList{
        public BazServiceList(IClientConfig config){
            super.initWithNiwsConfig(config);
        }
    }
     */
}
