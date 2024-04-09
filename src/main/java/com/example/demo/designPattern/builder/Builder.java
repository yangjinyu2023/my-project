package com.example.demo.designPattern.builder;

public abstract class Builder {
    protected Product product = new Product();
    protected Builder buildPart1(){
        product.setPart1("默认part1");
        return this;
    }
    protected Builder buildPart2(){
        product.setPart2("默认part2");
        return this;
    }
    protected Builder buildPart3(){
        product.setPart3("默认part3");
        return this;
    }

    public Product getResult(){
        return product;
    }
}
