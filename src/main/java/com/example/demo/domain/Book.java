package com.example.demo.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Spring启动，查找并加载需要被Spring管理的bean，进行Bean的实例化。
 * <p>
 * Bean实例化后对将Bean的引入和值注入到Bean的属性中。
 * <p>
 * 如果Bean实现了BeanNameAware接口的话，Spring将Bean的Id传递给setBeanName()方法。
 * <p>
 * 如果Bean实现了BeanFactoryAware接口的话，Spring将调用setBeanFactory()方法，将BeanFactory容器实例传入。
 * <p>
 * 如果Bean实现了ApplicationContextAware接口的话，Spring将调用Bean的setApplicationContext()方法，
 * 将bean所在应用上下文引用传入进来。
 * <p>
 * 如果Bean实现了BeanPostProcessor接口，Spring就将调用他们的postProcessBeforeInitialization()方法。
 * <p>
 * 如果Bean 实现了InitializingBean接口，Spring将调用他们的afterPropertiesSet()方法。
 * 类似的，如果bean使用init-method声明了初始化方法，该方法也会被调用。
 * <p>
 * 如果Bean 实现了BeanPostProcessor接口，Spring就将调用他们的postProcessAfterInitialization()方法。
 * <p>
 * 此时，Bean已经准备就绪，可以被应用程序使用了。他们将一直驻留在应用上下文中，直到应用上下文被销毁。
 * <p>
 * 如果bean实现了DisposableBean接口，Spring将调用它的destory()接口方法，
 * 同样，如果bean使用了destory-method 声明销毁方法，该方法也会被调用。
 */
public class Book implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {

    private String bookName;

    public Book(String bookName) {
        System.out.println("Book()");
        this.bookName = bookName;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName:" + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory:" + beanFactory.toString());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext:" + applicationContext.toString());
    }

    // 自定义初始化方法
    @PostConstruct
    public void springPostConstruct() {
        System.out.println("@PostConstruct");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet");
    }

    public void initBook() {
        System.out.println("initBook");
    }

    // 自定义销毁方法
    @PreDestroy
    public void springPareDestroy() {
        System.out.println("@PreDestroy");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean.destroy");
    }

    public void destroyBook() {
        System.out.println("destroyBook");
    }

}