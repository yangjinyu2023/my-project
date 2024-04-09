package com.example.demo.algorithm.practice;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * practice for heap
 *
 * @author yangjinyu
 * @time 2022/10/21 20:38
 */
public class HeapPractice {
    // 堆，无严格顺序，能保证root为最大（最大堆）
    static class Heap {
        int capacity;

        int size;

        int[] arr;

        public Heap(int capacity) {
            this.capacity = capacity;
            this.arr = new int[capacity];
        }

        public int max() {
            if (size == 0) {
                return -1;
            }
            return arr[0];
        }

        // 插入数组最后一个位置，循环向上和根比，如果大于根就交换位置
        public boolean add(int val) {
            if (size == capacity) {
                return false;
            }
            int currentIndex = size;
            arr[size] = val;
            while (currentIndex != 0) {
                int parentIndex = (currentIndex - 1) / 2;
                if (arr[currentIndex] > arr[parentIndex]) {
                    arr[currentIndex] = arr[parentIndex];
                    arr[parentIndex] = val;
                    currentIndex = parentIndex;
                }
                else {
                    break;
                }
            }
            size++;
            return true;
        }

        // 移除根结点，将最后一个节点放到根节点，然后循环向下交换到合适的位置
        public int remove() {
            if (size == 0) {
                return -1;
            }
            int val = arr[0];
            arr[0] = arr[--size];
            int curInx = 0;
            while (2 * curInx + 1 < size) {// 至少存在左节点
                int leftInx = 2 * curInx + 1;
                int rightInx = 2 * curInx + 2;
                int curVal = arr[curInx];
                if (rightInx < size) {// 如果存在左右两个节点，那么较大的节点一定大于等于curVal
                    if (arr[leftInx] > arr[rightInx]) {
                        arr[curInx] = arr[leftInx];
                        arr[leftInx] = curVal;
                        curInx = leftInx;
                    }
                    else {
                        arr[curInx] = arr[rightInx];
                        arr[rightInx] = curVal;
                        curInx = rightInx;
                    }
                }
                else if (arr[leftInx] > curVal) {// 如果不存在右节点，判断是否和左节点交换
                    arr[curInx] = arr[leftInx];
                    arr[leftInx] = curVal;
                    curInx = leftInx;
                }
                else {
                    break;
                }
            }
            return val;
        }
    }

    // 优先级队列，使用堆实现
    static class PriorityQueue {
        Heap heap;

        public PriorityQueue(int capacity) {
            this.heap = new Heap(capacity);
        }

        void offer(int val) {
            heap.add(val);
        }

        int poll() {
            return heap.remove();
        }

        int max() {
            return heap.max();
        }
    }

    // 单调队列，可维护出队顺序，可O(1)时间复杂度得到最值
    // 空间换时间
    static class MonotonicQueue {
        // 普通队列，维持出入队顺序
        Queue < Integer > queue = new LinkedList <>();

        // 单调队列，O(1)取最值
        Deque < Integer > deque = new LinkedList <>();

        void offer(int val) {
            queue.offer(val);
            while (!deque.isEmpty() && deque.peekLast() < val) {
                deque.pollLast();
            }
            deque.offerLast(val);
        }

        int poll() {
            if (queue.isEmpty() || deque.isEmpty()) {
                return -1;
            }
            if (queue.peek().equals(deque.peekFirst())) {
                deque.pollFirst();
            }
            return queue.poll();
        }

        int max() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.peekFirst();
        }
    }

    // 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        MonotonicQueue queue = new MonotonicQueue();
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            int j = i - k + 1;
            if(j >= 0) {
                res[i] = queue.max();
                queue.poll();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        System.out.println(heap.add(7));
        System.out.println(heap.add(4));
        System.out.println(heap.add(10));
        System.out.println(heap.add(9));
        System.out.println(heap.add(1));
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.add(5));
        System.out.println(heap.remove());
        System.out.println(heap.add(3));
        System.out.println(heap.add(8));
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println();
        MonotonicQueue queue = new MonotonicQueue();
        queue.offer(1);
        System.out.println(queue.max());
        queue.offer(2);
        System.out.println(queue.max());
        queue.offer(5);
        System.out.println(queue.max());
        queue.offer(4);
        System.out.println(queue.max());
        queue.poll();
        System.out.println(queue.max());
        queue.poll();
        System.out.println(queue.max());
        queue.poll();
        System.out.println(queue.max());
        queue.poll();
        System.out.println(queue.max());
        queue.offer(6);
        System.out.println(queue.max());
        queue.offer(3);
        System.out.println(queue.max());
        queue.offer(7);
        System.out.println(queue.max());
        queue.poll();
        System.out.println(queue.max());
        queue.poll();
        System.out.println(queue.max());
        queue.poll();
        System.out.println(queue.max());
    }
}