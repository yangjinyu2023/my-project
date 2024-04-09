package com.example.demo.designPattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

    private Object obj;

    //参数传入被代理对象，返回代理对象实例
    public Object getProxyInstance(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("任务开始");
        long time = (long) (Math.random() * 10000);
        Object result = method.invoke(obj, args);
        System.out.println("完成任务，用时：" + time);
        return result;
    }
}