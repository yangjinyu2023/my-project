package com.example.demo.designPattern.proxy.cglib;

public class SampleBean {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void test(){
        System.out.println("hello world");
    }
}