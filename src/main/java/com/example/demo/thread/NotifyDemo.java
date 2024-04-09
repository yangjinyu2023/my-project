package com.example.demo.thread;

public class NotifyDemo {

    private volatile boolean go = false;

    public static void main(String[] args) throws InterruptedException {
        final NotifyDemo demo = new NotifyDemo();

        Runnable waitTask = () -> {
            try {
                demo.shouldGo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        };

        Runnable notifyTask = () -> {
            demo.go();
            System.out.println(Thread.currentThread().getName() + " finished");
        };

        new Thread(waitTask, "waitTask1").start();
        new Thread(waitTask, "waitTask2").start();
        new Thread(waitTask, "waitTask3").start();

        Thread.sleep(200);

        new Thread(notifyTask, "notifyTask").start();
    }

    private synchronized void shouldGo() throws InterruptedException {
        while (!go) {
            System.out.println(Thread.currentThread().getName() + " is going to wait on this object");
            wait();
            System.out.println(Thread.currentThread().getName() + " is woken up");
        }
        go = false;// 重置状态
    }

    private synchronized void go() {
        while (!go) {
            System.out.println(Thread.currentThread().getName() + " is going to notify");
            go = true;
            //notify();
            notifyAll();
        }
    }


}