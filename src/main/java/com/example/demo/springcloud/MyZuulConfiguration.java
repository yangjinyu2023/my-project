package com.example.demo.springcloud;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//https://blog.csdn.net/qq_44112474/article/details/109094775
@EnableZuulProxy
@Configuration
public class MyZuulConfiguration {
    @Bean
    public MyZuulFilter preRequestLogFilter() {
        return new MyZuulFilter ();
    }
}