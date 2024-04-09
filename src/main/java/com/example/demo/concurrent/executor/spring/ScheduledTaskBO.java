package com.example.demo.concurrent.executor.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class ScheduledTaskBO {
    @Autowired
    private MyThreadPoolTaskScheduler scheduler;

    private CountDownLatch countDownLatch = new CountDownLatch(5);

//    @Async
//    @Scheduled(fixedRate = 5000)
//    public void execute() {
//        System.out.println("???????????????????????????" + 111);
//        if (countDownLatch.getCount() == 0) {
//            scheduler.cancelTask(this);
//        }
//        countDownLatch.countDown();
//    }

    @Async
    public void executeByMyself() {
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("???????????????????????????" + 111);
            if (countDownLatch.getCount() == 0) {
                scheduler.cancelTask("111");
            }
            countDownLatch.countDown();
        }, 5000, "111");
    }
}