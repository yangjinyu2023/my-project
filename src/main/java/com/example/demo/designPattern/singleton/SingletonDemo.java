package com.example.demo.designPattern.singleton;

public class SingletonDemo {
    public static void main(String[] args) {
        Singleton5 singleton5 = Singleton5.INSTNACE;
        singleton5.say();
        System.out.println(singleton5 == Singleton5.INSTNACE);
    }
}