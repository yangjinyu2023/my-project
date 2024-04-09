package com.example.demo.conversionService;

import org.springframework.stereotype.Component;

// 自定义转换器
@Component
public class MyCustomConverter implements CustomConverter<MyDO, MyDTO> {
    @Override
    public MyDTO convert(MyDO myDO) {
        return new MyDTO(myDO.getName());
    }
}