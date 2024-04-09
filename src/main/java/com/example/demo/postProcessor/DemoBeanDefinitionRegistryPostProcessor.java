package com.example.demo.postProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Component;

/**
 * 自定义BeanDefinitionRegistryPostProcessor，
 * <p>
 * 将自定义注解修饰的类beanDefinition注册到容器
 *
 * @author yangjinyu
 * @time 2021/10/15 14:53
 */
@Component
public class DemoBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathBeanDefinitionScanner scanner = new DemoAnnotationScanner(registry);
        scanner.scan(AutoConfigurationPackages.get(beanFactory).get(0));
        System.out.println("=======@DemoComponent扫描完成=======");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

}