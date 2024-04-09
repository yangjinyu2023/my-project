package com.example.demo.delivered.checker.force;

import org.springframework.stereotype.Component;

import com.example.demo.delivered.ForceDeliveryConfig;
import com.example.demo.delivered.checker.CheckResult;
import com.example.demo.delivered.checker.ForceDeliveryCheckerEnum;
import com.example.demo.delivered.checker.IChecker;
import com.example.demo.delivered.checker.ICheckerChain;

import lombok.extern.slf4j.Slf4j;

/**
 * 重量体积校验
 *
 * @author yangjinyu
 * @time 2023/4/18 11:18
 */
@Slf4j
@Component
public class WeightVolumeChecker implements IChecker<ForceDeliveryConfig> {

    @Override
    public int getCode() {
        return ForceDeliveryCheckerEnum.WEIGHT_VOLUME_CHECK.getCode();
    }

    @Override
    public CheckResult check(ForceDeliveryConfig param, ICheckerChain chain) {
        // 验证重量体积异常是否通过
        if (Math.random() * 100 <= 10) {
            return CheckResult.fail("该运单0重量0体积，不可强妥，请先完成重量体积补录");
        }
        return chain.proceed();
    }
}