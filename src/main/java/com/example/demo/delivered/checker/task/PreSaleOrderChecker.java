package com.example.demo.delivered.checker.task;

import org.springframework.stereotype.Component;

import com.example.demo.delivered.DeliveredTask;
import com.example.demo.delivered.checker.CheckResult;
import com.example.demo.delivered.checker.DeliveredTaskCheckerEnum;
import com.example.demo.delivered.checker.IChecker;
import com.example.demo.delivered.checker.ICheckerChain;

import lombok.extern.slf4j.Slf4j;

/**
 * 妥投任务预售单校验
 *
 * @author yangjinyu
 * @time 2023/4/6 16:04
 */
@Slf4j
@Component
public class PreSaleOrderChecker implements IChecker < DeliveredTask > {

    @Override
    public int getCode() {
        return DeliveredTaskCheckerEnum.PRE_SALE_ORDER_CHECK.getCode();
    }

    @Override
    public CheckResult check(DeliveredTask deliveredTask, ICheckerChain chain) {
        if (Math.random() * 100 <= 10) {
            return CheckResult.fail("预付单没有支付尾款，不能妥投");
        }
        return chain.proceed();
    }
}