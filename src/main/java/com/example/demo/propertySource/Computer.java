package com.example.demo.propertySource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
//https://blog.csdn.net/qq_37312838/article/details/108237678
@Data
@Component
@PropertySource("classpath:custom.properties")
@ConfigurationProperties(prefix = "computer")
public class Computer {
    private String name;
    private Double price;
    private Screen screen = new Screen();

    @Data
    public static class Screen {
        private String size;
    }
}