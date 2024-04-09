package com.example.demo.designPattern.singleton;

/**
 * @description: 懒汉式
 * @author: yangjinyu
 * @time: 2020/1/30 15:49
 */
public class Singleton2 {

    private static Singleton2 instance;

    // 类方法同步锁，保证线程安全
    // 每次获取实例都要加锁和释放锁，效率低
    public static synchronized Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}