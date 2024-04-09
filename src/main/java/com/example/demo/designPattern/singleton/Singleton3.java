package com.example.demo.designPattern.singleton;

/**
 * @description: 双重检查懒汉式
 * @author: yangjinyu
 * @time: 2020/1/30 15:49
 */
public class Singleton3 {
    // 禁止指令重排优化
    private static volatile Singleton3 instance;

    // 类代码块同步锁，保证线程安全
    public static Singleton3 getInstance(){
        // 第一次检测
        if(instance == null){
            // 只有第一次检测通过，才会加同步锁
            synchronized (Singleton3.class){
                // 第二次检测，防止在进入同步代码块之前被实例化
                if(instance == null){
                    instance = new Singleton3();
                }
            }
        }
        // 如果instance不加volatile，那么指令重排序了，instance != null但是有可能还没初始化，
        // 导致返回的instance是半成品
        return instance;
    }
}