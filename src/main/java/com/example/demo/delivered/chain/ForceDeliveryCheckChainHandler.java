package com.example.demo.delivered.chain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.example.demo.delivered.ForceDeliveryConfig;
import com.example.demo.delivered.checker.CheckResult;
import com.example.demo.delivered.checker.ForceDeliveryCheckerChain;
import com.example.demo.delivered.checker.IChecker;
import com.example.demo.delivered.checker.ICheckerChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ForceDeliveryCheckChainHandler
        implements ICheckChainHandler <ForceDeliveryConfig>, ApplicationContextAware, InitializingBean {
    private static final String PRE_FIX = "force.delivery.source-check-mapping-";

    @Autowired
    private List < IChecker < ForceDeliveryConfig > > chainList;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (chainList == null || chainList.isEmpty()) {
            throw new RuntimeException("强妥校验链为空，请确认");
        }
        // 按照code排序
        chainList = chainList.stream().sorted(Comparator.comparing(IChecker::getCode)).collect(Collectors.toList());
    }

    @Override
    public CheckResult check(ForceDeliveryConfig param) {
        // 获取 来源-校验项 映射关系
        String mapping = applicationContext.getEnvironment().getProperty(PRE_FIX + param.getSource());
        log.info("强妥校验，sourceCheckMapping={}", mapping);
        List < String > sourceMapping = null;
        if (StringUtils.isNotBlank(mapping)) {
            sourceMapping = Arrays.asList(mapping.split(","));
        }
        ICheckerChain chain = new ForceDeliveryCheckerChain(param, chainList, sourceMapping);
        return chain.proceed();
    }
}