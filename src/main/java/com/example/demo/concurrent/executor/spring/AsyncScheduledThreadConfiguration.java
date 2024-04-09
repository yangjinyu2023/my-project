package com.example.demo.concurrent.executor.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class AsyncScheduledThreadConfiguration {

    @Bean
    public MyThreadPoolTaskScheduler threadPoolTaskScheduler() {
        MyThreadPoolTaskScheduler scheduler = new MyThreadPoolTaskScheduler();
        scheduler.setPoolSize(2);
        scheduler.setThreadNamePrefix("threadPoolTaskScheduler-");
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setAwaitTerminationSeconds(60);
        return scheduler;
    }
}