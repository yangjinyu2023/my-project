package com.example.demo.designPattern.proxy.springAop;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class AopDemo implements IAopDemo {
    @Override
    public void say(String msg) {
        System.out.println("AopDemo.say : " + msg);
    }

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new AopDemo());
        factory.setProxyTargetClass(true);
        // 1. 添加 DefaultIntroductionAdvisor 顾问到 ProxyFactory 中
        Advisor advisor = new DefaultIntroductionAdvisor(new CustomIntroductionInterceptor(), IAopPlusDemo.class);
        factory.addAdvisor(advisor);

        Object proxy = factory.getProxy();
        // 执行 IAopDemo 的 say 方法
        IAopDemo aopDemo = (IAopDemo) proxy;
        aopDemo.say("IAopDemo");
        // 执行 引介增强 接口 IAopPlusDemo  的 sayPlus  方法
        IAopPlusDemo aopPlusDemo = (IAopPlusDemo) proxy;
        // 2. 调用 引介增强接口 IAopPlusDemo  的方法。这里实际会调用 CustomIntroductionInterceptor#sayPlus 方法
        aopPlusDemo.sayPlus("IAopPlusDemo");
    }
}