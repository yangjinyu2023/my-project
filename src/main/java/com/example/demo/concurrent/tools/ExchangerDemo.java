package com.example.demo.concurrent.tools;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ExchangerDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Exchanger<String> exchanger = new Exchanger<>();

        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " 我有苹果，等待别人和我交换");
            try {
                String food = exchanger.exchange("苹果");
                System.out.println(Thread.currentThread().getName() + " 我交换到了" + food);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " 我正在做蛋糕");
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " 蛋糕做好了，等待别人和我交换");
                String food = exchanger.exchange("蛋糕");
                System.out.println(Thread.currentThread().getName() + " 我交换到了" + food);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}