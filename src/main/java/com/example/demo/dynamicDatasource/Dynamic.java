package com.example.demo.dynamicDatasource;

import java.lang.annotation.*;

// 自定义 切换数据源的注解
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Dynamic{
    String value() default "";
}