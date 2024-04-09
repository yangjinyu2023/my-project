package com.example.demo.designPattern.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("任务开始");
        long time = (long) (Math.random() * 10000);
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("完成任务，用时：" + time);
        if (method.getName().equals("coding")) {
            System.out.println("写代码真辛苦");
        }
        return result;
    }
}

