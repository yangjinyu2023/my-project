package com.example.demo.algorithm.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <a href="https://blog.csdn.net/oBoySen/article/details/107901633">有三个线程顺序打印abc十次，请用线程同步实现</a>
 *
 * @author yangjinyu
 * @time 2022/10/19 10:28
 */
public class T3 {

    public static void main(String[] args) {
        new T3().cyclicPrint();
    }

    public void cyclicPrint() {
        // 和synchronized解法思路一致
        // 可以优化：定义多个condition，让每个线程等待在自己的condition，唤醒时唤醒对应的condition
        new Thread(new Task(0)).start();
        new Thread(new Task(1)).start();
        new Thread(new Task(2)).start();
    }

    private int count;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();


    class Task implements Runnable{
        private final int remainder;

        public Task(int remainder) {
            this.remainder = remainder;
        }

        @Override
        public void run() {
            while(count < 30){
                lock.lock();
                try {
                    if(count < 30 && count % 3 == remainder){
                        System.out.println(Thread.currentThread() +"=>"+ count);
                        count++;
                        condition.signalAll();
                    }else{
                        condition.await();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    //class Task implements Runnable {
    //    private final String word;
    //
    //    public Task(String word) {
    //        this.word = word;
    //    }
    //
    //    @Override
    //    public void run() {
    //        while (count < 30) {
    //            lock.lock();
    //            try {
    //                if (match(word)) {
    //                    System.out.println(word);
    //                    count++;
    //                }
    //                else {
    //                    condition.await();
    //                }
    //                condition.signalAll();
    //            }
    //            catch (InterruptedException e) {
    //                e.printStackTrace();
    //            }
    //            finally {
    //                lock.unlock();
    //            }
    //        }
    //    }
    //
    //    private boolean match(String s) {
    //        if (count % 3 == 0 && s.equals("A")) {
    //            return true;
    //        }
    //        if (count % 3 == 1 && s.equals("B")) {
    //            return true;
    //        }
    //        if (count % 3 == 2 && s.equals("C")) {
    //            return true;
    //        }
    //        return false;
    //    }
    //}
}