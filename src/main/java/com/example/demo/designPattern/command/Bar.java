package com.example.demo.designPattern.command;

public class Bar implements Receiver{
    @Override
    public void action() {
        System.out.println("提供美酒");
    }
}