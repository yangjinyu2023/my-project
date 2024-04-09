package com.example.demo.designPattern.proxy.proxyFactory;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.SpringProxy;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ProxyFactoryDemo {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Demo());
        proxyFactory.setInterfaces(DemoInterface.class);
        proxyFactory.addAdvice(new LogAfterAdvice());
        proxyFactory.addAdvice(new LogBeforeAdvice());
        proxyFactory.addAdvice((MethodBeforeAdvice) (method, args1, target) ->
                System.out.println("AAAAAAAA你被拦截了：方法名为：" + method.getName() +
                        " 参数为--" + Arrays.asList(args1)));
        proxyFactory.addAdvice((MethodBeforeAdvice) (method, args2, target) ->
                System.out.println("BBBBBBBB你又被拦截了：方法名为：" + method.getName() +
                        " 参数为--" + Arrays.asList(args2)));
        DemoInterface demo = (DemoInterface) proxyFactory.getProxy();
        demo.hello("YJY");
        //class com.example.demo.designPattern.proxy.proxyFactory.ProxyFactoryDemo$Demo
        System.out.println(proxyFactory.getTargetClass());
        //SingletonTargetSource for target object [com.example.demo.designPattern.proxy.proxyFactory.ProxyFactoryDemo$Demo@77846d2c]
        System.out.println(proxyFactory.getTargetSource());
        //[interface com.example.demo.designPattern.proxy.proxyFactory.ProxyFactoryDemo$DemoInterface]
        System.out.println(Arrays.asList(proxyFactory.getProxiedInterfaces()));
        //[org.springframework.aop.support.DefaultPointcutAdvisor:
        // pointcut [Pointcut.TRUE];
        // advice [com.example.demo.designPattern.proxy.proxyFactory.ProxyFactoryDemo$LogAfterAdvice@548ad73b],
        // org.springframework.aop.support.DefaultPointcutAdvisor:
        // pointcut [Pointcut.TRUE];
        // advice [com.example.demo.designPattern.proxy.proxyFactory.ProxyFactoryDemo$LogBeforeAdvice@4c762604],
        // org.springframework.aop.support.DefaultPointcutAdvisor:
        // pointcut [Pointcut.TRUE];
        // advice [com.example.demo.designPattern.proxy.proxyFactory.ProxyFactoryDemo$$Lambda$1/546718765@2641e737],
        // org.springframework.aop.support.DefaultPointcutAdvisor:
        // pointcut [Pointcut.TRUE];
        // advice [com.example.demo.designPattern.proxy.proxyFactory.ProxyFactoryDemo$$Lambda$2/750044075@727803de]]
        System.out.println(Arrays.asList(proxyFactory.getAdvisors()));

        // 获取类型，看看是JDK代理还是cglib的
        System.out.println(demo instanceof Proxy); //false
        System.out.println(demo instanceof SpringProxy); //true
        System.out.println(demo.getClass()); //class com.example.demo.designPattern.proxy.proxyFactory.$Proxy0
        System.out.println(Proxy.isProxyClass(demo.getClass())); //false
        System.out.println(AopUtils.isCglibProxy(demo)); //false

        // Object的方法 ==== 所有的Object方法都不会被AOP代理 这点需要注意
        System.out.println(demo.equals(new Object()));//false
        System.out.println(demo.hashCode());//2107621412
        System.out.println(demo.getClass());//class com.example.demo.designPattern.proxy.proxyFactory.$Proxy0

        // 其余方法都没被拦截  只有toString()被拦截了？
        //before log...
        //AAAAAAAA你被拦截了：方法名为：toString 参数为--[]
        //BBBBBBBB你又被拦截了：方法名为：toString 参数为--[]
        //after log...
        //com.example.demo.designPattern.proxy.proxyFactory.ProxyFactoryDemo$Demo@77846d2c
        System.out.println(demo.toString());
    }

    interface DemoInterface {
        void hello(String name);
    }

    static class Demo implements DemoInterface {
        @Override
        public void hello(String name) {
            System.out.println("hello " + name);
        }
    }

    // 前置增强
    static class LogBeforeAdvice implements MethodBeforeAdvice {
        @Override
        public void before(Method method, Object[] args, Object target) throws Throwable {
            System.out.println("before log...");
        }
    }

    // 后置增强
    static class LogAfterAdvice implements AfterReturningAdvice {
        @Override
        public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
            System.out.println("after log...");
        }
    }
}