package com.example.demo.test;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;

/**
 * @author yangjinyu
 * @time 2023/11/14 18:50
 */
public class TT2 {
    @Getter
    @Setter
    private Predicate<Void> predicate;
}