package com.example.demo.postProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ConfigurationDemo {
    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean
    public Bean2 bean2() {
        Bean2 bean2 = new Bean2();
        System.out.println("=============" + bean1().toString());
        return bean2;
    }

    @Bean
    public Bean2 bean22() {
        Bean2 bean2 = new Bean2();
        System.out.println("=============" + bean1().toString());
        return bean2;
    }
    // 最终输出的bean1是同一个对象
    //=============com.example.demo.postProcessor.Bean1@24d61e4
    //=============com.example.demo.postProcessor.Bean1@24d61e4
    //这里就是因为ConfigurationClassPostProcessor#postProcessBeanFactory方法通过代理实现了该效果，以保证正确语义。
    //尝试把@Configuration改成@Component，发现打印出两个对象
    //=============com.example.demo.postProcessor.Bean1@7add838c
    //=============com.example.demo.postProcessor.Bean1@3662bdff
    //postProcessBeanFactory 方法中对 Full 类型(即被 @Configuration 修饰的配置类)的配置类进行了动态代理

    // 所以@Bean不一定要配合@Configuration，
    // 有@Component，或者@Import被引入，被spring识别为bean对象，就可以了。
}