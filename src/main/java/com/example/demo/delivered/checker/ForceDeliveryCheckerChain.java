package com.example.demo.delivered.checker;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.demo.delivered.ForceDeliveryConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ForceDeliveryCheckerChain implements ICheckerChain {
    private final ForceDeliveryConfig config;
    private final List<IChecker<ForceDeliveryConfig>> checkerList;
    private final List<String> sourceMapping;
    private int currentIndex = -1;

    public ForceDeliveryCheckerChain(ForceDeliveryConfig config, List<IChecker<ForceDeliveryConfig>> checkerList, List<String> sourceMapping) {
        this.config = config;
        this.checkerList = checkerList;
        this.sourceMapping = sourceMapping;
    }

    public CheckResult proceed() {
        if (this.currentIndex == this.checkerList.size() - 1) {
            return new CheckResult();
        }
        IChecker<ForceDeliveryConfig> checker = this.checkerList.get(++this.currentIndex);
        // 匹配则执行checker，否则继续链路的下个checker
        if (matches(checker)) {
            log.info("{}开始执行校验项code={}", config.getWaybillCode(), checker.getCode());
            return checker.check(this.config, this);
        } else {
            return proceed();
        }
    }

    private boolean matches(IChecker<ForceDeliveryConfig> checker) {
        if (CollectionUtils.isNotEmpty(sourceMapping)) {
            int code = checker.getCode();
            return sourceMapping.contains(String.valueOf(code)) || ForceDeliveryCheckerEnum.isPermanent(code);
        }
        return true;
    }
}