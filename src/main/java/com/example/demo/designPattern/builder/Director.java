package com.example.demo.designPattern.builder;

/**
 * 指挥者
 *
 * @author yangjinyu
 * @time 2022/4/9 11:27
 */
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product construct() {
        return builder.buildPart1().buildPart2().buildPart3().getResult();
    }
}