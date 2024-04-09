package com.example.demo.propertySource;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:custom.properties")
public class Pad {
    @Value("${pad.name}")
    private String name;

    @Value("${pad.price}")
    private Double price;
}