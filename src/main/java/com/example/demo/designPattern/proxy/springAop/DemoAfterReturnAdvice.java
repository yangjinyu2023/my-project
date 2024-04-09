package com.example.demo.designPattern.proxy.springAop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class DemoAfterReturnAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("DemoAfterReturnAdvice.afterReturning");
    }
}