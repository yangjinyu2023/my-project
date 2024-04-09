package com.example.demo.designPattern.proxy;

public class JavaDeveloper implements Developer, Tester {
    @Override
    public void coding() {
        System.out.println("编写java程序");
    }

    @Override
    public void unitTest() {
        System.out.println("进行java程序单元测试");
    }

    @Override
    public void functionTest() {
        System.out.println("进行java程序功能测试");
    }
}