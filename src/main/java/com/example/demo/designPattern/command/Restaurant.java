package com.example.demo.designPattern.command;

public class Restaurant implements Receiver{
    @Override
    public void action() {
        System.out.println("提供美食");
    }
}