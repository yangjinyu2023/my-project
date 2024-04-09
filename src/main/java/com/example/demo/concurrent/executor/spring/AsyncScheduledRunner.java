package com.example.demo.concurrent.executor.spring;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.transaction.mapper.LegendsMapper;

@Component
public class AsyncScheduledRunner implements CommandLineRunner {
    @Autowired
    private ScheduledTaskBO bo;

    @Autowired
    private List<LegendsMapper> legendsMappers;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate1;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate2;

    @Override
    public void run(String... args) throws Exception {
        // bo.execute();
        bo.executeByMyself();
        System.out.println("???????????????????????????" + 222);
    }
}