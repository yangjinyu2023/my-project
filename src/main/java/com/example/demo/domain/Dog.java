package com.example.demo.domain;

import org.springframework.stereotype.Component;

@Component("dog")
public class Dog implements Pet {
    @Override
    public void behavior() {
        System.out.println("dog running");
    }
}