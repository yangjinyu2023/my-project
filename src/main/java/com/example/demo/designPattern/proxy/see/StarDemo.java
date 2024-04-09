package com.example.demo.designPattern.proxy.see;

public class StarDemo {
    public static void main(String[] args) {
        Star liu = new Liu();
        StarJDKProxy jdkProxy = new StarJDKProxy();
        jdkProxy.setStar(liu);
        jdkProxy.getProxyInstance().sing();

        StarCglibProxy cglibProxy = new StarCglibProxy();
        ((Liu) cglibProxy.getProxyInstance(liu)).sing();
    }
}