package com.example.demo.concurrent.executor.threadPool;

public class ForLoopCalculator implements Calculator{
    @Override
    public long sumUp(long[] numbers) {
        long total = 0;
        for (long i : numbers) {
            total += i;
        }
        return total;
    }
}