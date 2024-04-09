package com.example.demo.concurrent.sequence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 主键获取工具，提升获取主键效率
 *
 * @author yangjinyu
 * @time 2021/3/22 17:27
 */
public class PrimaryKeyGenerator {

    private AtomicInteger count = new AtomicInteger();

    private ConcurrentLinkedQueue<String> queue;

    /**
     * 私有化构造方法，使外部无法通过构造方法构造除instance外的类实例，从而达到单例模式控制类实例数目的目的
     */
    private PrimaryKeyGenerator() {
        queue = new ConcurrentLinkedQueue<>(getDataBaseSequence(200));
    }

    public String getPrimaryKey() {
        String primaryKey = queue.poll();
        if (primaryKey == null) {
            synchronized (this) {
                primaryKey = queue.poll();
                if (primaryKey == null) {
                    queue.addAll(getDataBaseSequence(200));
                }else {
                    return primaryKey;
                }
            }
        }

        return primaryKey;
    }

    private List<String> getDataBaseSequence(int num) {
        // 模拟从数据库获取序列集合
        List<String> primaryKeyList = new ArrayList<>();
        int index = count.get();
        for (int i = num * index; i < num * (index + 1); i++) {
            primaryKeyList.add(String.valueOf(i));
        }
        count.getAndIncrement();
        return primaryKeyList;
    }

    /**
     * 类级内部类，用于缓存类实例 该类将在被调用时才会被装载，从而实现了延迟加载
     * 同时由于instance采用静态初始化的方式，因此JVM能保证其线程安全性
     */
    private static class Instance {
        private static PrimaryKeyGenerator generator = new PrimaryKeyGenerator();
    }

    /**
     * 类实例的全局访问方法 添加static关键词使得外部可以通过类名直接调用该方法获取类实例
     */
    public static PrimaryKeyGenerator getInstance() {
        return Instance.generator;
    }
}