package com.example.demo.importSelector;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MyImportSelector.class, NoConfigurationAnnotation.class})
public @interface MyApplication {
}