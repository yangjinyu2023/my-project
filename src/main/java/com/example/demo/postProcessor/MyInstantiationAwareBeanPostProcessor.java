package com.example.demo.postProcessor;

import com.example.demo.domain.Engineer1;
import com.example.demo.domain.Engineer2;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass == Engineer1.class) {
            Engineer1 engineer = new Engineer1();
            engineer.setName("周狗子");
            return engineer;
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Nullable
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
            throws BeansException {
        if(bean instanceof Engineer2){
            ((Engineer2) bean).setName("娜依土鳖公主");
        }
        return null;
    }

}