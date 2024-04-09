package com.example.demo.A_lc;//如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数
//值排序之后中间两个数的平均值。 
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例 1： 
//
// 输入：
//["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
//[[],[1],[2],[],[3],[]]
//输出：[null,null,null,1.50000,null,2.00000]
// 
//
// 示例 2： 
//
// 输入：
//["MedianFinder","addNum","findMedian","addNum","findMedian"]
//[[],[2],[],[3],[]]
//输出：[null,null,2.00000,null,2.50000] 
//
// 
//
// 限制： 
//
// 
// 最多会对 addNum、findMedian 进行 50000 次调用。 
// 
//
// 注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-
//stream/ 
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 340 👎 0


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {

    // 时间复杂度O(n^2)，空间复杂度O(n)，使用堆进行优化
    /*private List<Integer> list;*/

    // 用两个堆存放数据，A中元素全部大于B中元素，并且A.size >= B.size
    // A为小顶堆（最小的元素在堆顶），B为大顶堆
    private PriorityQueue<Integer> A = new PriorityQueue<>();// 默认从小到大排列

    private PriorityQueue<Integer> B = new PriorityQueue<>((x, y) -> y - x);// (x,y)->y-x，从大到小排列

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        /*list = new ArrayList<>();*/
    }

    public void addNum(int num) {
        // 插入排序
         /*
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
        */
        // 大顶堆、小顶堆
        if (A.size() != B.size()) {
            A.add(num);
            B.add(A.poll());
        } else {
            B.add(num);
            A.add(B.poll());
        }
    }

    public double findMedian() {
        /*
        if (list.isEmpty()) {
            return 0d;
        }
        if ((list.size() & 1) != 0) {
            return list.get(list.size() / 2);
        } else {
            return (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / (double) 2;
        }
        */
        int size = A.size() + B.size();
        if ((size & 1) != 0) {
            return A.peek();
        } else {
            return (A.peek() + B.peek()) / (double) 2;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
