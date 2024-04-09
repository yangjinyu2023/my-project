package com.example.demo.lambda;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/4/13 20:28
 */
@FunctionalInterface
public interface Conventer<F,T> {
    T convert(F from);
}