package com.example.demo.delivered.checker.task;

import org.springframework.stereotype.Component;

import com.example.demo.delivered.DeliveredTask;
import com.example.demo.delivered.checker.CheckResult;
import com.example.demo.delivered.checker.DeliveredTaskCheckerEnum;
import com.example.demo.delivered.checker.IChecker;
import com.example.demo.delivered.checker.ICheckerChain;

import lombok.extern.slf4j.Slf4j;

/**
 * 妥投任务结转历史校验
 *
 * @author yangjinyu
 * @time 2023/4/4 15:57
 */
@Slf4j
@Component
public class TransferHistoryChecker implements IChecker < DeliveredTask > {

    @Override
    public int getCode() {
        return DeliveredTaskCheckerEnum.TRANSFER_HISTORY_CHECK.getCode();
    }

    @Override
    public CheckResult check(DeliveredTask deliveredTask, ICheckerChain chain) {
        if (Math.random() * 100 <= 10) {
            return CheckResult.fail("已结转");
        }
        return chain.proceed();
    }
}