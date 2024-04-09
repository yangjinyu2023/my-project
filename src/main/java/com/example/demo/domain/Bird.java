package com.example.demo.domain;

import org.springframework.stereotype.Component;

/**
 * 鸟
 * @author yangjinyu
 * @time 2022/12/1 8:58
 */
@Component("bird")
public class Bird implements Pet {
    @Override
    public void behavior() {
        System.out.println("bird flying");
    }
}