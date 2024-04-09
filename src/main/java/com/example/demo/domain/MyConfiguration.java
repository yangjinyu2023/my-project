package com.example.demo.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean(initMethod = "initBook", destroyMethod = "destroyBook")
    public Book book(){
        return new Book("java");
    }

    @Bean
    public Book book2(Dog dog2){
        System.out.println("++++++++++++++++book2"+dog2.toString());
        return new Book(dog2.toString());
    }

    @Bean
    public Dog dog2(){
        System.out.println("++++++++++++++dog2");
        return new Dog();
    }

    @Bean
    public Dog dog007(){
        return new Dog();
    }

    @Bean
    public Dog dog9527(){
        return new Dog();
    }
}