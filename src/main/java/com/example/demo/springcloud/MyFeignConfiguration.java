package com.example.demo.springcloud;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import feign.Request;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableHystrix // 该注解集成@EnableCircuitBreaker，用于开启服务熔断
@EnableFeignClients(basePackages = "com.example.demo.springcloud")
@Configuration
public class MyFeignConfiguration {

    // 自定义连接和响应超时时间
    @Bean
    public Request.Options feignRequestOptions() {
        return new Request.Options(10000, 10000);
    }

    // 自定义ribbon负载均衡策略
    @Bean
    public IRule ribbonRule() {
        // 默认就是这个
        // 在BaseLoadBalancer中
        // private final static IRule DEFAULT_RULE = new RoundRobinRule();
        return new RoundRobinRule();
    }
}