package com.example.demo.algorithm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LeetCode 146，利用LinkedHashMap实现LRU
 *
 * @author yangjinyu
 * @time 2022/10/1 14:29
 */
class LRUCache_LinkedHashMap extends LinkedHashMap<Integer, Integer> {

    private static final long serialVersionUID = -4761397393715381641L;

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("我是你爸爸哈哈哈哈");
        int end = Math.min(100, builder.length());
        System.out.println(builder.substring(0, end));
        List<String> list = new ArrayList<>();
        list.add("1");
        List<String> out = list.stream().filter(e->e.equals("2")).collect(Collectors.toList());
        System.out.println(out);
        // ["LRUCache","put","put","get","put","get","put","get","get","get"]
        // [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        LRUCache_LinkedHashMap lru = new LRUCache_LinkedHashMap(2);
        lru.put(1, 1);
        lru.print();
        lru.put(2, 2);
        lru.print();
        lru.get(1);
        lru.print();
        lru.put(3, 3);
        lru.print();
        lru.get(2);
        lru.print();
        lru.put(4, 4);
        lru.print();
        lru.get(1);
        lru.print();
        lru.get(3);
        lru.print();
        lru.get(4);
        lru.print();
        System.out.println();
    }

    private final int capacity;
    public LRUCache_LinkedHashMap(int capacity) {
        super(capacity,0.75F, true);// 调用父类构造函数，要传参accessOrder为true
        this.capacity = capacity;
    }

    public int get(int key) {
        return getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    // 重写removeEldestEntry方法
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public void print() {
        super.values().forEach(System.out::print);
        System.out.println();
    }
}