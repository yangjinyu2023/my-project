package com.example.demo.importSelector;

import org.springframework.context.annotation.Bean;

public class NoConfigurationAnnotation {
    // 说明@Bean并不一定要在类上有@Configuration注解，被@Import引入同样可以
    @Bean
    public DemoBean1002 demoBean1002(){
        return new DemoBean1002();
    }
}