package com.example.demo.postProcessor;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * 自定义BeanDefinitionScanner，指定includeFilter，只有被定义注解修饰的类才会生成BeanDefinition
 * @author yangjinyu
 * @time 2021/10/15 14:58
 */
public class DemoAnnotationScanner extends ClassPathBeanDefinitionScanner {
    public DemoAnnotationScanner(BeanDefinitionRegistry registry) {
        super(registry);
        // 添加包含条件， 只有被 @DemoComponent 注解修饰的类才会生成BeanDefinition
        addIncludeFilter(new AnnotationTypeFilter(DemoComponent.class));
    }
}