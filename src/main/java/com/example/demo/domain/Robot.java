package com.example.demo.domain;

import lombok.Data;

@Data
public class Robot {

    public static String id = "007";

    private String name;

    static {
        System.out.println("------------link class-------------");
    }

    private String whisperings(String something) {
        return "whisperings " + something;
    }

    public String say(String something, int times) {
        return "say " + something + " " + times + " times";
    }

    public static void sing() {
        System.out.println("I am singing");
    }
}