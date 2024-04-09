package com.example.demo.delivered.checker.task;

import org.springframework.stereotype.Component;

import com.example.demo.delivered.DeliveredTask;
import com.example.demo.delivered.checker.CheckResult;
import com.example.demo.delivered.checker.DeliveredTaskCheckerEnum;
import com.example.demo.delivered.checker.IChecker;
import com.example.demo.delivered.checker.ICheckerChain;

import lombok.extern.slf4j.Slf4j;

/**
 * 妥投任务拒收换新单校验
 *
 * @author yangjinyu
 * @time 2023/4/6 16:02
 */
@Slf4j
@Component
public class ReturnChangeOrderChecker implements IChecker < DeliveredTask > {

    @Override
    public int getCode() {
        return DeliveredTaskCheckerEnum.RETURN_CHANGE_ORDER_CHECK.getCode();
    }

    @Override
    public CheckResult check(DeliveredTask deliveredTask, ICheckerChain chain) {
        if (Math.random() * 100 <= 10) {
            return CheckResult.fail("逆向换新单，妥投校验不通过，不能妥投");
        }
        return chain.proceed();
    }

}