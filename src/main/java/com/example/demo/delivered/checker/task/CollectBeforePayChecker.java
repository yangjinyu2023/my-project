package com.example.demo.delivered.checker.task;

import org.springframework.stereotype.Component;

import com.example.demo.delivered.DeliveredTask;
import com.example.demo.delivered.checker.CheckResult;
import com.example.demo.delivered.checker.DeliveredTaskCheckerEnum;
import com.example.demo.delivered.checker.IChecker;
import com.example.demo.delivered.checker.ICheckerChain;

import lombok.extern.slf4j.Slf4j;

/**
 * 妥投任务先揽后付校验
 *
 * @author yangjinyu
 * @time 2023/4/6 15:52
 */
@Slf4j
@Component("collectBeforePayCheckerForDeliveredTask")
public class CollectBeforePayChecker implements IChecker < DeliveredTask > {

    @Override
    public int getCode() {
        return DeliveredTaskCheckerEnum.COLLECT_BEFORE_PAY_CHECK.getCode();
    }

    @Override
    public CheckResult check(DeliveredTask deliveredTask, ICheckerChain chain) {
        if (Math.random() * 100 <= 10) {
            return CheckResult.fail("先揽后付运单，未支付妥投会造成货损，禁止妥投");
        }
        return chain.proceed();
    }
}