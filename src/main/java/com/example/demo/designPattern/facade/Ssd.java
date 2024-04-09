package com.example.demo.designPattern.facade;

/** * 硬盘类 */
class Ssd implements Computer {
    @Override
    public void open() {
        System.out.println("启动硬盘");
    }
}