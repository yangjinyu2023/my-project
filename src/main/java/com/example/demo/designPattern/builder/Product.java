package com.example.demo.designPattern.builder;

import lombok.Data;

import java.util.Arrays;

@Data
public class Product {
    private String part1;
    private String part2;
    private String part3;

    @Override
    public String toString() {
        return Arrays.asList(part1, part2, part3).toString();
    }
}