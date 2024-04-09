package com.example.demo.designPattern.proxy.see;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class StarCglibProxy implements MethodInterceptor {

    public Object getProxyInstance(Object obj){
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(obj.getClass());
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("检票");
        return methodProxy.invokeSuper(obj, args);
    }
}