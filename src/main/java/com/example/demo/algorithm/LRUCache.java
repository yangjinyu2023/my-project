package com.example.demo.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://blog.csdn.net/wangxilong1991/article/details/70172302">
 * LRU淘汰策略缓存
 * </a>
 * LRU -- Least Recently Used Cache
 * 思想：HashMap做容器存放缓存对象
 * 维护一个双端队列（给缓存对象加上pre和next，给LRUCache加上first，last）
 * put操作时，将缓存对象放到first，元素数量超过capacity，则remove last
 *
 * @author yangjinyu
 * @time 2021/9/3 15:25
 */
public class LRUCache<K, V> {
    static class CacheNode<K, V> {
        CacheNode<K, V> pre;
        CacheNode<K, V> next;
        K key;
        V value;
    }

    private final int capacity;
    private final Map<K, CacheNode<K, V>> caches;
    private CacheNode<K, V> first;
    private CacheNode<K, V> last;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.caches = new HashMap<>(capacity);
    }

    public void put(K k, V v) {
        CacheNode<K, V> node = caches.get(k);
        if (node == null) {
            if (caches.size() >= capacity) {
                caches.remove(last.key);
                removeLast();
            }
            node = new CacheNode<>();
            caches.put(k, node);
            node.key = k;
        }
        node.value = v;
        moveToFirst(node);
    }

    public Object get(K k) {
        CacheNode<K, V> node = caches.get(k);
        if (node == null) {
            return null;
        }
        moveToFirst(node);
        return node.value;
    }

    public void clear() {
        first = null;
        last = null;
        caches.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        CacheNode<K, V> node = first;
        while (node != null) {
            sb.append(String.format("[%s:%s]", node.key, node.value));
            node = node.next;
        }
        return sb.toString();
    }

    private void moveToFirst(CacheNode<K, V> node) {
        if (first == null || last == null) {
            first = last = node;
            return;
        }
        if (first == node) {
            return;
        }
        // 从双端链表原位置断开
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        // 判断是否为尾结点，是的话需要给last重新赋值
        if (node == last) {
            last = node.pre;
        }
        node.next = first;
        first.pre = node;
        first = node;
        first.pre = null;
    }

    private void removeLast() {
        if (last != null) {
            last = last.pre;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
        }
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "a");
        System.out.println(lruCache);
        lruCache.put(2, "b");
        System.out.println(lruCache);
        lruCache.put(3, "c");
        System.out.println(lruCache);
        lruCache.put(4, "d");
        System.out.println(lruCache);
        lruCache.put(1, "aa");
        System.out.println(lruCache);
        lruCache.put(2, "bb");
        System.out.println(lruCache);
        lruCache.put(5, "e");
        System.out.println(lruCache);
        lruCache.get(1);
        System.out.println(lruCache);
        lruCache.put(1, "aaa");
        System.out.println(lruCache);
        lruCache.clear();
        System.out.println(lruCache);
    }
}