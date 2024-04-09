package com.example.demo.delivered.checker.force;

import com.example.demo.delivered.DeliveredTask;
import org.springframework.stereotype.Component;

import com.example.demo.delivered.ForceDeliveryConfig;
import com.example.demo.delivered.checker.CheckResult;
import com.example.demo.delivered.checker.ForceDeliveryCheckerEnum;
import com.example.demo.delivered.checker.IChecker;
import com.example.demo.delivered.checker.ICheckerChain;
import com.example.demo.delivered.checker.ITest;

import lombok.extern.slf4j.Slf4j;

/**
 * 商家开启“禁止强制妥投”校验
 *
 * @author yangjinyu
 * @time 2023/4/18 11:18
 */
@Slf4j
@Component
public class CustomerForbidChecker implements IChecker< ForceDeliveryConfig >, ITest<ForceDeliveryConfig, DeliveredTask> {

    @Override
    public int getCode() {
        return ForceDeliveryCheckerEnum.CUSTOMER_FORBID_CHECK.getCode();
    }

    @Override
    public CheckResult check(ForceDeliveryConfig param, ICheckerChain chain) {
        if (Math.random() * 100 <= 10) {
            return CheckResult.fail("该商家单禁止强妥");
        }
        return chain.proceed();
    }
}