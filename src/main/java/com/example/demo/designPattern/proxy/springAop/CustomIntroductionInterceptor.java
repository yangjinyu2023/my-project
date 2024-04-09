package com.example.demo.designPattern.proxy.springAop;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

// 引介拦截器的实现。这里注意，如果使用DelegatingIntroductionInterceptor，
// 则需要强制实现增强的接口，比如这里是 IAopPlusDemo 接口
public class CustomIntroductionInterceptor extends DelegatingIntroductionInterceptor implements  IAopPlusDemo {
    @Override
    public void sayPlus(String msg) {
        System.out.println("CustomIntroductionInterceptor.sayPlus : " + msg);
    }
}