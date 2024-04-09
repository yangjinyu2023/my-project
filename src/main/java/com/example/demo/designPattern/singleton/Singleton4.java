package com.example.demo.designPattern.singleton;

/**
 * @description: 静态内部类方式
 * @author: yangjinyu
 * @time: 2020/1/30 15:49
 */
public class Singleton4 {

    private static class SingletonInstance{
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    public static Singleton4 getInstance(){
        // 调用内部类的类变量时，内部类才被加载，instance才被实例化
        return SingletonInstance.INSTANCE;
    }
}