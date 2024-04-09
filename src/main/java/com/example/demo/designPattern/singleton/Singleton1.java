package com.example.demo.designPattern.singleton;

/**
 * @description: 饿汉式
 * @author: yangjinyu
 * @time: 2020/1/30 15:49
 */
public class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();

    // 类初始化时已经创建好对象，线程安全
    // 如果实例未被使用，内存就浪费了
    public static Singleton1 getInstance(){
        return INSTANCE;
    }
}