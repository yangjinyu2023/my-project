package com.example.demo.designPattern.proxy.jdk;

import java.lang.reflect.Proxy;

import com.example.demo.designPattern.proxy.Developer;
import com.example.demo.designPattern.proxy.JavaDeveloper;
import com.example.demo.designPattern.proxy.PythonDeveloper;
import com.example.demo.designPattern.proxy.Tester;

public class JdkDynamicProxyClient {

    // jdk动态代理
    // 实现java.lang.reflect.InvocationHandler接口
    // 使用java.lang.reflect.Proxy创建代理类
    // 需要被代理类必须实现接口
    // 可根据被代理对象创建不同的代理类
    public static void main(String[] args) {
        new JdkDynamicProxyClient().test();

        // 通过设置，把生成的代理类的字节码保存在com/sun/proxy
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Developer javaDeveloper = new JavaDeveloper();
        Developer pythonDeveloper = new PythonDeveloper();

        // 可根据被代理对象创建不同的代理类
        Developer javaDeveloperProxy = (Developer) new MyInvocationHandler().getProxyInstance(javaDeveloper);
        javaDeveloperProxy.coding();
        javaDeveloperProxy.unitTest();

        Developer pythonDeveloperProxy = (Developer) new MyInvocationHandler().getProxyInstance(pythonDeveloper);
        pythonDeveloperProxy.coding();
        pythonDeveloperProxy.unitTest();

        // 由于使用反射method.invoke()
        // 接口中新增方法甚至新增接口，只要代理逻辑不变，就不需要创建新的代理类
        Tester javaTesterProxy = (Tester) new MyInvocationHandler().getProxyInstance(javaDeveloper);
        javaTesterProxy.functionTest();
    }

    private void test() {
        Developer javaDeveloper = new JavaDeveloper();
        System.out.println("1：" + javaDeveloper);
        Developer proxyDeveloper = (Developer) Proxy.newProxyInstance(Developer.class.getClassLoader(),
                new Class[] { Developer.class }, (proxy, method, args) -> {
                    Object result = null;
                    if (method.getName().equals("coding")) {
                        System.out.println("2：starting coding! " + proxy.toString());
                        result = method.invoke(javaDeveloper, args);
                    }
                    if (method.getName().equals("unitTest")) {
                        System.out.println("3：so boring! " + proxy.toString());
                        result = method.invoke(javaDeveloper, args);
                    }
                    return result;
                });
        proxyDeveloper.coding();
        proxyDeveloper.unitTest();
    }
}