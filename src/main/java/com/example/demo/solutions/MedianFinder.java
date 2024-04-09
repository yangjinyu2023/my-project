package com.example.demo.solutions;

import java.util.ArrayList;
import java.util.List;

class MedianFinder {

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        finder.addNum(3);
        System.out.println(finder.findMedian());
    }

    private List<Integer> list;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        // 插入排序
        list.add(num);
        int i;
        for (i = list.size() - 2; i >= 0; i--) {
            if (list.get(i) > num) {
                list.set(i + 1, list.get(i));
                list.set(i, num);
            } else {
                break;
            }
        }
    }

    public double findMedian() {
        if (list.isEmpty()) {
            return 0d;
        }
        if ((list.size() & 1) != 0) {
            return list.get(list.size() / 2);
        } else {
            return (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / (double) 2;
        }
    }
}