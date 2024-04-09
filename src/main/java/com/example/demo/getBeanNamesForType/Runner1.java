package com.example.demo.getBeanNamesForType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner1 implements ApplicationRunner {
    @Autowired
    private My1Bean2 bean2;
    //@Autowired
    //private My1Bean1 bean1;
    //getBeanNamesForType会根据class类型筛选出容器中所有子类的名字
    //因此注入My1Bean1时，根据类型找到my1Bean1和my1Bean2，两者都没有primary，
    //报错：required a single bean, but 2 were found
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("XXXXXXXXXXXX"+bean2);
        //System.out.println("XXXXXXXXXXXX"+bean1);
    }
}