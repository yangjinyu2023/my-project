package com.example.demo.A_lc;

import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是
 * O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 * <p>
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * <p>
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 * <p>
 * <p>
 * Related Topics 设计 队列 单调队列 👍 398 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class MaxQueue {

    Queue<Integer> queue;
    Deque<Integer> deque;

    // 单调队列，实现复杂度O(1)的求最值队列
    public MaxQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }

    public int max_value() {
        if (deque.isEmpty()) {
            return -1;
        }
        return deque.peekFirst();
    }

    // 理解好均摊时间复杂度：总共发生n次，n-1次时间复杂度是0(1)，1次是O(n)，那么均分时间复杂度是O(1)
    // 例如 543216，最后一次时间复杂度为O(n)，其他每次操作的时间复杂度都是 O(1)，均摊时间复杂度为 (O(1)×(n−1)+O(n))/n=O(1)。
    public void push_back(int value) {
        queue.offer(value);
        // 理解好单调队列是关键：
        // 5进队后，4、3、2、1都比前面的小，依次进队，6比前面的都大，最后保留一个6
        // 在6没入队之前max一直是5，5出队后max是4，4出队后max是3……
        // 6最后入队，6入队后max一定是6，直到6出队，因此前面的都可以poll掉
        while (!deque.isEmpty() && deque.peekLast() < value) {
            deque.pollLast();
        }
        deque.offerLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        if (deque.peekFirst().equals(queue.peek())) {
            deque.pollFirst();
        }
        return queue.poll();
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
//leetcode submit region end(Prohibit modification and deletion)
