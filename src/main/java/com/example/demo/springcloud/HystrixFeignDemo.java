package com.example.demo.springcloud;

import feign.hystrix.HystrixFeign;

public class HystrixFeignDemo {
    public static void main(String[] args) {
        // 1
        DataManageApi dataManageApi = HystrixFeign.builder()
                .decoder(new MyDecoder())
                .target(DataManageApi.class, "https://api.github.com");
        dataManageApi.getString("111");
        // 2
    }
}