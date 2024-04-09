package com.example.demo.designPattern.proxy;



/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/2/16 11:39
 */
public class PythonDeveloper implements Developer {

    @Override
    public void coding() {
        System.out.println("编写python程序");
    }

    @Override
    public void unitTest() {
        System.out.println("进行python程序单元测试");
    }
}