package com.example.demo.concurrent.executor.future;

import java.util.concurrent.Callable;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/1/28 12:01
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        String value = "finished";
        System.out.println("ready to work");
        Thread.sleep(5000);
        System.out.println("task done");
        return value;
    }
}