package com.example.demo.createBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class D {

    // 虽然内部类不是独立类（!isIndependent）,但是类D是，并且有@Component注解
    // 所以doScan时虽然不认可E，但是processMemberClasses时会处理有@Component类的带@Configuration的内部类
    // 所以"aa"能成功注册
    @Configuration
    public class E{
        @Bean
        public A aa(){
            return new A();
        }
    }
}