package com.example.demo.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BElement extends BaseElement implements IElement{
    @Value("${my.value:}")
    private String value;//不带这个冒号，没有对应配置会报错！
    @Override
    public void execute() {
        log.info("B execute");
        properties.execute();
        System.out.println("++++++++"+value);//输出了++++++++空串
    }

    public static void main(String[] args) {
        String a = "123456";
        System.out.println(a.length());
        System.out.println(a.charAt(5-1));
    }
}