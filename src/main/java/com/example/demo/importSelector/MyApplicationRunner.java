package com.example.demo.importSelector;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.aspectj.Fan;
import com.example.demo.domain.Dog;
import com.example.demo.domain.Engineer1;
import com.example.demo.domain.Engineer2;
import com.example.demo.test.AElement;
import com.example.demo.test.BElement;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    // 启动类通过@Import引入MyImportSelector，通过selectImports()读取spring.factories引入
    @Autowired
    private DemoBean1001 demoBean1001;
    // 启动类通过@Import引入NoConfigurationAnnotation，虽然无@Configuration，一样可以使用@Bean注入demoBean1002
    @Autowired
    private DemoBean1002 demoBean1002;
    // 会把所以Dog类型的bean注入到dogMap，key为beanName。
    @Autowired
    //@Qualifier("dog2")
    private Map<String, Dog> dogMap;
    // InstantiationAwareBeanPostProcessor可以自定义bean的创建

    @Autowired
    private Dog dog2;//dog2注入了，说明根据名称beanName筛选了，还是要用@Qualifier("dog2")比较好
    @Autowired
    private Engineer1 engineer1;
    @Autowired
    private Engineer2 engineer2;
    @Autowired
    private Fan fan;

    @Autowired
    private AElement aElement;

    @Autowired
    private BElement bElement;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        demoBean1001.printMsg();
        demoBean1002.printMsg();
        System.out.println(dogMap.toString());
        System.out.println(engineer1.getName() + engineer2.getName());
        fan.fan();
        aElement.execute();
        bElement.execute();
        System.out.println();
    }
}