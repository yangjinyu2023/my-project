package com.example.demo.designPattern.builder;

public class MyBuilder2 extends Builder{
    @Override
    protected Builder buildPart1(){
        product.setPart1("MyBuilder2 part1");
        return this;
    }
    @Override
    protected Builder buildPart2(){
        product.setPart2("MyBuilder2 part2");
        return this;
    }
    @Override
    protected Builder buildPart3(){
        product.setPart3("MyBuilder2 part3");
        return this;
    }
}