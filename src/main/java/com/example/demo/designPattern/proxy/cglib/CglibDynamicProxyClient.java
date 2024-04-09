package com.example.demo.designPattern.proxy.cglib;

import com.example.demo.designPattern.proxy.JavaDeveloper;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

public class CglibDynamicProxyClient {

    // Cglib动态代理
    // 实现org.springframework.cglib.proxy.MethodInterceptor接口
    // 使用org.springframework.cglib.proxy.Enhancer创建代理类对象
    // 基于继承实现代理
    // 无法对static、final类进行代理
    // 无法对private、static方法进行代理
    public static void main(String[] args) {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\demo");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(JavaDeveloper.class);
        enhancer.setCallback(new MyMethodInterceptor());
        JavaDeveloper javaDeveloperProxy = (JavaDeveloper) enhancer.create();
        javaDeveloperProxy.coding();
        javaDeveloperProxy.unitTest();
        javaDeveloperProxy.functionTest();
    }
}