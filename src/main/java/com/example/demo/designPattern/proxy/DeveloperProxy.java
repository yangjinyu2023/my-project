package com.example.demo.designPattern.proxy;

public class DeveloperProxy implements Developer {

    private Developer developer;

    public DeveloperProxy(Developer developer) {
        this.developer = developer;
    }

    @Override
    public void coding() {
        System.out.println("任务开始");
        long time = (long) (Math.random() * 10000);
        developer.coding();
        System.out.println("完成任务，用时：" + time);
    }

    @Override
    public void unitTest() {
        System.out.println("任务开始");
        long time = (long) (Math.random() * 10000);
        developer.unitTest();
        System.out.println("完成任务，用时：" + time);
    }
}