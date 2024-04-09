package com.example.demo.thread;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable interruptTask = () -> {
            int i = 0;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(1000);
                    i++;
                    System.out.println(Thread.currentThread().getName() + "(" + Thread.currentThread().getState() + ") loop" + i);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "(" + Thread.currentThread().getState() + ") catch InterruptedException");
            }
        };

        Thread t1 = new Thread(interruptTask, "t1");
        System.out.println(t1.getName() + "(" + t1.getState() + ") is new");

        t1.start();
        System.out.println(t1.getName() + "(" + t1.getState() + ") is started");

        Thread.sleep(300);
        System.out.println(t1.getName() + "(" + t1.getState() + ") is sleeping");
        t1.interrupt();
        System.out.println(t1.getName() + "(" + t1.getState() + ") is interrupting");

        Thread.sleep(300);
        System.out.println(t1.getName() + "(" + t1.getState() + ") is interrupted now");
    }
}