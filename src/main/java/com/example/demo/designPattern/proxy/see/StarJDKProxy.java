package com.example.demo.designPattern.proxy.see;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StarJDKProxy implements InvocationHandler {
    private Star star;

    public void setStar(Star star) {
        this.star = star;
    }

    public Star getProxyInstance() {
        return (Star) Proxy.newProxyInstance(star.getClass().getClassLoader(),
                star.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("检票");
        return method.invoke(star, args);
    }
}