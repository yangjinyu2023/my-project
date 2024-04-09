package com.example.demo.A_lc;//定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
//
// 
//
// 示例: 
//
// MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.min();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.min();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// 各函数的调用总次数不超过 20000 次 
// 
//
// 
//
// 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/ 
// Related Topics 栈 设计 👍 363 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {

    // 辅助栈法，很巧妙，背下来吧。。。
    private Stack<Integer> stackA;

    private Stack<Integer> stackB;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        this.stackA = new Stack<>();
        this.stackB = new Stack<>();
    }

    public void push(int x) {
        stackA.push(x);
        if (stackB.isEmpty() || stackB.peek() >= x) {
            stackB.push(x);
        }
    }

    public void pop() {
        if (stackA.pop().equals(stackB.peek())) {
            stackB.pop();
        }
    }

    public int top() {
        return stackA.peek();
    }

    public int min() {
       return stackB.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
//leetcode submit region end(Prohibit modification and deletion)
