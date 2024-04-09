package com.example.demo.aspectj;

import org.springframework.stereotype.Component;

@Component
public class Fan implements IFan{
    @MyFan
    @Override
    public void fan(){
        System.out.println("fan");
    }
}