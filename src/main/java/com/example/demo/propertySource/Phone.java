package com.example.demo.propertySource;

import org.springframework.boot.context.properties.ConfigurationProperties;

//可以在启动类或配置类上添加@EnableConfigurationProperties(Phone.class)
//相当于把使用 @ConfigurationProperties的类（Phone.class）进行了一次注入
@ConfigurationProperties(prefix = "phone")
public class Phone {
}