package com.example.demo.thread;

public class SynchronizedDemo {
    public static void main(String[] args) {
        // 1.验证异步、同步对象锁锁代码块、同步对象锁锁方法
        SyncRunnable task = new SyncRunnable();
        Thread A_thread1 = new Thread(task, "A_thread1");
        Thread A_thread2 = new Thread(task, "A_thread2");
        Thread B_thread1 = new Thread(task, "B_thread1");
        Thread B_thread2 = new Thread(task, "B_thread2");
        Thread C_thread1 = new Thread(task, "C_thread1");
        Thread C_thread2 = new Thread(task, "C_thread2");
        A_thread1.start();
        A_thread2.start();
        B_thread1.start();
        B_thread2.start();
        C_thread1.start();
        C_thread2.start();

        // 2.验证不同对象之间的对象锁，互不影响
//        Thread A_thread1 = new Thread(new SyncRunnable(), "A_thread1");
//        Thread A_thread2 = new Thread(new SyncRunnable(), "A_thread2");
//        Thread B_thread1 = new Thread(new SyncRunnable(), "B_thread1");
//        Thread B_thread2 = new Thread(new SyncRunnable(), "B_thread2");
//        Thread C_thread1 = new Thread(new SyncRunnable(), "C_thread1");
//        Thread C_thread2 = new Thread(new SyncRunnable(), "C_thread2");
//        A_thread1.start();
//        A_thread2.start();
//        B_thread1.start();
//        B_thread2.start();
//        C_thread1.start();
//        C_thread2.start();

        // 3.验证异步、同步类锁锁代码块、同步类锁锁方法
//        SyncRunnable task = new SyncRunnable();
//        Thread A_thread1 = new Thread(task, "A_thread1");
//        Thread A_thread2 = new Thread(task, "A_thread2");
//        Thread D_thread1 = new Thread(task, "D_thread1");
//        Thread D_thread2 = new Thread(task, "D_thread2");
//        Thread E_thread1 = new Thread(task, "E_thread1");
//        Thread E_thread2 = new Thread(task, "E_thread2");
//        A_thread1.start();
//        A_thread2.start();
//        D_thread1.start();
//        D_thread2.start();
//        E_thread1.start();
//        E_thread2.start();

        // 4.验证不同对象但是相同类类型对象时，类锁生效
//        Thread B_thread1 = new Thread(new SyncRunnable(), "B_thread1");
//        Thread B_thread2 = new Thread(new SyncRunnable(), "B_thread2");
//        Thread D_thread1 = new Thread(new SyncRunnable(), "D_thread1");
//        Thread D_thread2 = new Thread(new SyncRunnable(), "D_thread2");
//        Thread E_thread1 = new Thread(new SyncRunnable(), "E_thread1");
//        Thread E_thread2 = new Thread(new SyncRunnable(), "E_thread2");
//        B_thread1.start();
//        B_thread2.start();
//        D_thread1.start();
//        D_thread2.start();
//        E_thread1.start();
//        E_thread2.start();

        // 4.验证对象锁和类锁互不影响
        // 因为作用的不是同一个对象，对象锁作用的是对象实例，类锁作用的是类类型对象
        //Thread C_thread1 = new Thread(new SyncRunnable(), "C_thread1");
        //Thread C_thread2 = new Thread(new SyncRunnable(), "C_thread2");
        //Thread E_thread1 = new Thread(new SyncRunnable(), "E_thread1");
        //Thread E_thread2 = new Thread(new SyncRunnable(), "E_thread2");
        //C_thread1.start();
        //C_thread2.start();
        //E_thread1.start();
        //E_thread2.start();
    }
}