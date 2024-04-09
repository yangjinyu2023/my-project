package com.example.demo.algorithm.practice;

import java.util.Stack;

import org.junit.Test;

/**
 * 栈练习
 *
 * @author yangjinyu
 * @time 2022/10/26 22:28
 */
public class StackPractice {
    @Test
    public void test() {
        ImitateQueue queue = new ImitateQueue();
        queue.appendTail(7);
        queue.appendTail(5);
        queue.appendTail(9);
        System.out.println(queue.deleteHead());
        MinStack stack = new MinStack();
        stack.push(3);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(4);
        System.out.println(stack.min());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.min());
        stack.push(5);
        System.out.println(stack.min());
        stack.push(1);
        System.out.println(stack.min());
    }

    // 剑指offer-31
    // 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
    // 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
    // 序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack < Integer > stack = new Stack <>();// 辅助栈
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && i < popped.length && stack.peek() == popped[i]) {
                i++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    // 剑指offer-30
    // 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
    // 调用 min、push 及 pop 的时间复杂度都是 O(1)。
    static class MinStack {
        // 单调栈，和单调队列思路类似
        Stack < Integer > stack1 = new Stack <>();

        Stack < Integer > stack2 = new Stack <>();

        public void push(int x) {
            stack1.push(x);
            if (stack2.isEmpty() || stack2.peek() >= x) {// 注意这里一定要有等号
                stack2.push(x);
            }
        }

        public void pop() {
            if (stack1.pop() == stack2.peek()) {
                stack2.pop();
            }
        }

        public int min() {
            return stack2.peek();
        }
    }

    // 剑指offer-9
    // 两个栈实现队列
    static class ImitateQueue {
        Stack < Integer > in = new Stack <>();

        Stack < Integer > out = new Stack <>();

        public void appendTail(int value) {
            in.push(value);
        }

        public int deleteHead() {
            if (out.isEmpty()) {
                if (in.isEmpty()) {
                    return -1;
                }
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
            return out.pop();
        }
    }
}