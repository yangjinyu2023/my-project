package com.example.demo.delivered.chain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.delivered.DeliveredTask;
import com.example.demo.delivered.ForceDeliveryConfig;
import com.example.demo.delivered.checker.CheckResult;
import com.example.demo.delivered.checker.IChecker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DeliveredTaskCheckChainHandler implements ICheckChainHandler < DeliveredTask >, InitializingBean {
    @Autowired
    private List < IChecker > chainList1;

    @Autowired
    private List < IChecker > chainList2;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 按照code排序
        chainList1 = chainList1.stream().filter(checker -> checker.isGenericsAdaptive(DeliveredTask.class))
                .sorted(Comparator.comparing(IChecker::getCode)).collect(Collectors.toList());
        chainList2 = chainList2.stream().filter(checker -> checker.isGenericsAdaptive(ForceDeliveryConfig.class))
                .sorted(Comparator.comparing(IChecker::getCode)).collect(Collectors.toList());
    }

    @Override
    public CheckResult check(DeliveredTask deliveredTask) {
        // ICheckerChain chain = new DeliveredTaskCheckerChain(deliveredTask, chainList);
        // return chain.proceed();
        return null;
    }
}