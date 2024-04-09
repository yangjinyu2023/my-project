package com.example.demo.lifecycle;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class MyLifeCycle implements SmartLifecycle {
    @Override
    public void start() {
        System.out.println("&&&&&&&&&&&MyLifeCycle.start()&&&&&&&&&&&");
    }

    @Override
    public void stop() {
        System.out.println("&&&&&&&&&&&MyLifeCycle.stop()&&&&&&&&&&&");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

}