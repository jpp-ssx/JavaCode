package com.kaikeba.consumer;

import com.kaikeba.consumer.irule.CustomRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

//指定service所在的包，开启openfeign客户端
@EnableFeignClients(basePackages = "com.kaikeba.consumer.service")
@EnableEurekaClient
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    //设置负载均衡随机策略
//    @Bean
//    public IRule loadBalanceRule() {
//        return new RandomRule();
//    }
    @Bean
    public IRule loadBalanceRule() {
        List<Integer> excludeports = new ArrayList<>();
        excludeports.add(8082);
        return new CustomRule(excludeports);
    }
}
