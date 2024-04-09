package com.example.demo.conversionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import javax.annotation.PostConstruct;
import java.util.Map;

@Configuration
public class CustomConvertersAutoRegistrar {

    @Configuration
    static class GenericConversionServiceConfig {
        // 注册一个 GenericConversionService （如何容器中没有的话），实例为 DefaultConversionService
        // 使用静态内部类保证优先注册
        @ConditionalOnMissingBean(value = GenericConversionService.class)
        @Bean
        public GenericConversionService genericConversionService() {
            return new DefaultConversionService();
        }
    }

    @Autowired
    GenericConversionService genericConversionService;

    @Autowired(required = false)
    Map<String, CustomConverter> customConverterMap;


    @PostConstruct
    public void init() {
        // 将所有类型为CustomConverter，都添加到genericConversionService
        if (customConverterMap != null) {
            customConverterMap.values().forEach(genericConversionService::addConverter);
        }
    }
}