package com.example.demo.factorybean;

import com.example.demo.domain.Aa60DTO;
import com.example.demo.domain.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean2 implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return new Aa60DTO();
    }

    @Override
    public Class<?> getObjectType() {
        return Aa60DTO.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
