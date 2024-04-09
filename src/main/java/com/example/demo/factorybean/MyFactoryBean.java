package com.example.demo.factorybean;

import com.example.demo.domain.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return new Data();
    }

    @Override
    public Class<?> getObjectType() {
        return Data.class;
    }
}
