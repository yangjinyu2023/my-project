package com.example.demo.designPattern.facade;

/** * 内存类 */
class Ddr implements Computer {
    @Override
    public void open() {
        System.out.println("启动内存");
    }
}