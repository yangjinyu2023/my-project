package com.example.demo.designPattern.builder;

/**
 * 建造者模式
 *
 * @author yangjinyu
 * @time 2022/4/9 11:26
 */
public class BuilderDemo {
    public static void main(String[] args) {
        Director director = new Director(new MyBuilder1());
        System.out.println(director.construct());
        director = new Director(new MyBuilder2());
        System.out.println(director.construct());
    }
}