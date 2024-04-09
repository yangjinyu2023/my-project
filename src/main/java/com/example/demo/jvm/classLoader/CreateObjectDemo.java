package com.example.demo.jvm.classLoader;

/**
 * 区分类加载和对象创建
 * @author yangjinyu
 * @time 2021/9/28 17:27
 */
public class CreateObjectDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("==>");
        Class clazz = Class.forName("com.example.demo.jvm.classLoader.OneObject");
        System.out.println("==>");
        clazz.newInstance();
        System.out.println("结论：类加载过程，会对静态变量初始化；对象创建过程，会对实例变量初始化");
    }
}