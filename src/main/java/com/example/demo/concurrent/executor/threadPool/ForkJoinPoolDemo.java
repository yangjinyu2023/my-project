package com.example.demo.concurrent.executor.threadPool;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class ForkJoinPoolDemo {
    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000L).toArray();

        Instant start = Instant.now();
        // 1.普通for循环
//        Calculator calculator = new ForLoopCalculator();
        // 2.经典多线程
//        Calculator calculator = new ExecutorServiceCalculator();
        // 3.fork-join
        Calculator calculator = new ForkJoinCalculator();
        long result = calculator.sumUp(numbers);
        // 4.Stream并行流
//        long result = LongStream.rangeClosed(0, 10000000L).parallel().reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("结果为：" + result);
    }
}