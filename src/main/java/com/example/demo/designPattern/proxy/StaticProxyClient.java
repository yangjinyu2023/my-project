package com.example.demo.designPattern.proxy;

public class StaticProxyClient {

    // 静态代理，代理类和被代理类实现同样的接口
    public static void main(String[] args) {
        Developer javaDeveloper = new JavaDeveloper();
        Developer pythonDeveloper = new PythonDeveloper();

        DeveloperProxy javaDeveloperProxy = new DeveloperProxy(javaDeveloper);
        DeveloperProxy pythonDeveloperProxy = new DeveloperProxy(pythonDeveloper);

        // 如果接口中新增一个方法，每个代理类都需要实现接口方法
        javaDeveloperProxy.coding();
        pythonDeveloperProxy.coding();
        javaDeveloperProxy.unitTest();
        pythonDeveloperProxy.unitTest();

        // 如果实现新接口，需要创建新的代理类
        Tester javaTester = new JavaDeveloper();
        TesterProxy javaTesterProxy = new TesterProxy(javaTester);
        javaTesterProxy.functionTest();
    }

}