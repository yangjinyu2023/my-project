package com.example.demo.lambda;

@FunctionalInterface
public interface Formula {

    double calculate(int a);

    // 扩展方法
    // 可以给接口添加非抽象的方法实现
    default double increment(int a) {
        return ++a;
    }
}