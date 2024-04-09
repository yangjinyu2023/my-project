package com.example.demo.conversionService;

import org.springframework.core.convert.converter.Converter;
// 统一所有自定义转换器的类型，方便后续自动注册
public interface CustomConverter<S, T> extends Converter<S, T> {
}