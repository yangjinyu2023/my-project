package com.example.demo.designPattern.chain;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class AspectJAdviceDemo {

    static class MyAspectJAroundAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("execute myAspectJAroundAdvice");
            return invocation.proceed();
        }
    }

    static class MyAspectJBeforeAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("execute myAspectJBeforeAdvice");
            return invocation.proceed();
        }
    }

    static class MyAspectJAfterAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            try {
                return invocation.proceed();
            } finally {
                System.out.println("execute myAspectJAfterAdvice");
            }
        }
    }

    static class MyAspectJAfterThrowingAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            try {
                return invocation.proceed();
            } catch (Exception e) {
                System.out.println("execute myAspectJAfterThrowingAdvice");
                throw e;
            }
        }
    }

    static class MyMethodInvocation implements MethodInvocation {
        private Object object;
        private Method method;
        private Object[] args;

        public MyMethodInvocation(Object object, Method method, Object[] args) {
            this.object = object;
            this.method = method;
            this.args = args;
        }

        private List<MethodInterceptor> interceptors = new ArrayList<>();

        {
            interceptors.add(new MyAspectJAroundAdvice());
            interceptors.add(new MyAspectJAfterThrowingAdvice());
            interceptors.add(new MyAspectJAfterAdvice());
            interceptors.add(new MyAspectJBeforeAdvice());
        }

        private int currentInterceptorIndex = -1;

        public Object proceed() throws Throwable {
            if (this.currentInterceptorIndex == this.interceptors.size() - 1) {
                return method.invoke(object, args);
            }

            MethodInterceptor interceptor =
                    this.interceptors.get(++this.currentInterceptorIndex);
            return interceptor.invoke(this);
        }

        @Override
        public Object getThis() {
            return this;
        }

        @Override
        public AccessibleObject getStaticPart() {
            return null;
        }

        @Override
        public Method getMethod() {
            return method;
        }

        @Override
        public Object[] getArguments() {
            return args;
        }
    }

    interface TargetClass {
        void targetMethod(String arg);
    }

    static class MyTargetClass implements TargetClass {

        @Override
        public void targetMethod(String arg) {
            System.out.println(arg + "this is target output");
        }
    }


    // 责任链模式
    // 职责链上的处理者负责处理请求，客户只需要将请求发送到职责链上即可，无须关心请求的处理细节和请求的传递
    // 所以职责链将请求的发送者和请求的处理者解耦了
    public static void main(String[] args) {
        MyTargetClass myTargetClass = new MyTargetClass();
        Object proxyObject = Proxy.newProxyInstance(myTargetClass.getClass().getClassLoader(),
                new Class<?>[]{TargetClass.class},
                (proxy, method, args1) -> new MyMethodInvocation(myTargetClass, method, args1).proceed());
        ((TargetClass) proxyObject).targetMethod("hello world");

    }
}