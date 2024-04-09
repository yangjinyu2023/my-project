package com.example.demo.designPattern.proxy;

public class TesterProxy implements Tester {

    private Tester tester;

    public TesterProxy(Tester tester) {
        this.tester = tester;
    }


    @Override
    public void functionTest() {
        System.out.println("任务开始");
        long time = (long) (Math.random() * 10000);
        tester.functionTest();
        System.out.println("完成任务，用时：" + time);
    }
}