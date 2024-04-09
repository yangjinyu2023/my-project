package com.example.demo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/2/11 14:48
 */
@Component
public class Person {

    private Long id;

    private String name;

    @Autowired
    @Qualifier("bird")
    private Pet pet;

    public void callPet(){
        pet.behavior();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}