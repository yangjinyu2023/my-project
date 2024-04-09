package com.example.demo.delivered.checker;

import java.util.List;

import com.example.demo.delivered.DeliveredTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeliveredTaskCheckerChain implements ICheckerChain {
    private final DeliveredTask deliveredTask;
    private final List<IChecker<DeliveredTask>> checkerList;
    private int currentIndex = -1;

    public DeliveredTaskCheckerChain(DeliveredTask deliveredTask, List<IChecker<DeliveredTask>> checkerList) {
        this.deliveredTask = deliveredTask;
        this.checkerList = checkerList;
    }

    public CheckResult proceed() {
        if (this.currentIndex == this.checkerList.size() - 1) {
            return new CheckResult();
        }
        IChecker<DeliveredTask> checker = this.checkerList.get(++this.currentIndex);
        log.info("{}开始执行妥投任务校验项code={}", deliveredTask.getWaybillCode(), checker.getCode());
        return checker.check(this.deliveredTask, this);
    }
}