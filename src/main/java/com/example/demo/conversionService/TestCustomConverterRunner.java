package com.example.demo.conversionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;

@Component
public class TestCustomConverterRunner implements ApplicationRunner {
    @Autowired
    GenericConversionService genericConversionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // genericConversionService根据source和target类型自动匹配converter
        System.out.println(genericConversionService.convert(new MyDO("hello"), MyDTO.class));
    }
}