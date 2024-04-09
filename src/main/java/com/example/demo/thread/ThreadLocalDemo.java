package com.example.demo.thread;

public class ThreadLocalDemo {
    static class MyRunnable implements Runnable{
        ThreadLocal<String> local = new ThreadLocal<>();
        @Override
        public void run() {
            local.set("111");
            System.out.println(local.get());
            local.remove();
        }
    }

    public static void main(String[] args) {
        new Thread(new MyRunnable()).start();
        new MyRunnable().run();
    }
}