package com.example.demo.jvm.classLoader;

import com.example.demo.domain.Robot;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 加载类， loadClass 和 Class.forName 的区别
 * @author: yangjinyu
 * @time: 2020/1/18 20:43
 */

public class LoadDifferenceDemo {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Robot.class.getClassLoader();
        System.out.println("ClassLoader.loadClass");
        classLoader.loadClass("com.example.demo.domain.Robot");
        System.out.println("Class.forName");
        Class clazz = Class.forName("com.example.demo.domain.Robot");
        System.out.println((clazz == Robot.class));
    }

}