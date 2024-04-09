package com.example.demo.designPattern.facade;

/** * CPU类 */
class Cpu implements Computer {
    @Override
    public void open() {
        System.out.println("启动CPU");
    }
}