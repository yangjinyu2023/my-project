package com.example.demo.createBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yangjinyu
 * @time 2022/2/21 13:58
 */
@Component
public class B {
    @Autowired
    A a;
}