package com.example.demo.delivered.checker.force;

import org.springframework.stereotype.Component;

import com.example.demo.delivered.ForceDeliveryConfig;
import com.example.demo.delivered.checker.CheckResult;
import com.example.demo.delivered.checker.ForceDeliveryCheckerEnum;
import com.example.demo.delivered.checker.IChecker;
import com.example.demo.delivered.checker.ICheckerChain;

import lombok.extern.slf4j.Slf4j;

/**
 * 医药类型订单校验
 *
 * @author yangjinyu
 * @time 2023/4/18 11:18
 */
@Slf4j
@Component
public class MedicineOrderChecker implements IChecker< ForceDeliveryConfig > {

    @Override
    public int getCode() {
        return ForceDeliveryCheckerEnum.MEDICINE_ORDER_CHECK.getCode();
    }

    @Override
    public CheckResult check(ForceDeliveryConfig param, ICheckerChain chain) {
        if (Math.random() * 100 <= 10) {
            return CheckResult.fail("医药类型订单，不可强妥");
        }
        return chain.proceed();
    }
}