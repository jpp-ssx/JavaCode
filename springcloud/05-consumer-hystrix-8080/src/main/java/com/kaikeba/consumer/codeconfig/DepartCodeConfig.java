package com.kaikeba.consumer.codeconfig;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DepartCodeConfig {
    @LoadBalanced//开启消费端的负载均衡策略 默认是轮训策略
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
