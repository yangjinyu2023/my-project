package com.example.demo.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    @Pointcut("@annotation(com.example.demo.aspectj.MyFan)")
    public void pointcut(){
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("xxxxxxxxxxxxx--around--xxxxxxxxxxxxx");
        joinPoint.proceed();
        System.out.println("xxxxxxxxxxxxx--around--xxxxxxxxxxxxx");
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("xxxxxxxxxxxxx--before--xxxxxxxxxxxxx");
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        System.out.println("xxxxxxxxxxxxx--after--xxxxxxxxxxxxx");
    }
}