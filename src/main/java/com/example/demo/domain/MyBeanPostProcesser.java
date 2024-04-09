package com.example.demo.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcesser implements BeanPostProcessor {
    // 当一个BeanPostProcessor的实现类注册到Spring IOC容器后
    // 对于该Spring IOC容器所创建的每个bean实例在初始化方法（如afterPropertiesSet和任意已声明的init方法）调用前
    // 将会调用BeanPostProcessor中的postProcessBeforeInitialization方法
    // 而在bean实例初始化方法调用完成后，则会调用BeanPostProcessor中的postProcessAfterInitialization方法
    // 我们可以修改bean的属性，可以给bean生成一个动态代理实例等等。
    // 一些Spring AOP的底层处理也是通过实现BeanPostProcessor来执行代理包装逻辑的。
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization:" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization:" + beanName);
        return bean;
    }
}