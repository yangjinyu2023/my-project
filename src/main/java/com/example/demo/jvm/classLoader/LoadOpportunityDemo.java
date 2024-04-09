package com.example.demo.jvm.classLoader;

import com.example.demo.domain.Robot;

/**
 * @description: 类的加载时机
 * @author: yangjinyu
 * @time: 2020/1/30 17:41
 */
public class LoadOpportunityDemo {
    // 总结：类只会被加载一次
    public static void main(String[] args) throws ClassNotFoundException {
        // 创建类的实例时，类被加载
        //new Robot();
        // 调用类的static方法时，类被加载
        //Robot.sing();
        // 调用类变量，类被加载
        System.out.println(Robot.id);

        // 使用Class.forName
        //Class.forName("com.example.demo.domain.Robot");

        // 使用ClassLoader.loadClass
        //ClassLoader classLoader = LoadOpportunityDemo.class.getClassLoader();
        //classLoader.loadClass("com.example.demo.domain.Robot");
    }
}