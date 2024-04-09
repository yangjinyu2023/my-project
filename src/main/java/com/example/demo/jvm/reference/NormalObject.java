package com.example.demo.jvm.reference;

public class NormalObject {
    public String name;

    public NormalObject(String name) {
        this.name = name;
    }

    @Override
    protected void finalize(){
        System.out.println("finalize NormalObject" + name);
    }

    @Override
    public String toString(){
        return name;
    }
}