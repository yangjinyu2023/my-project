package com.example.demo.datastructure.recursion;

import java.util.Stack;

/**
 * 汉诺塔
 *
 * @author yangjinyu
 * @time 2021/6/10 15:02
 */
public class Hanoi {

    /**
     * 汉诺塔
     * <p>假设有 N 个盘子在塔座A上，我们将其看为两个盘子，其中(N-1)~1个盘子看成是一个盘子，最下面第N个盘子看成是一个盘子</p>
     * <p>  (1)、从初始塔座A上移动包含n-1个盘子到中介塔座B上。</p>
     * <p>  (2)、将初始塔座A上剩余的一个盘子（最大的一个盘子）放到目标塔座C上。</p>
     * <p>  (3)、将中介塔座B上n-1个盘子移动到目标塔座C上。</p>
     */
    public static void hanoi_recursion(Stack<Integer> from, Stack<Integer> temp, Stack<Integer> to, int dish) {
        if (dish == 1) {
            to.push(from.pop());
        } else {
            hanoi_recursion(from, to, temp, dish - 1);
            to.push(from.pop());
            hanoi_recursion(temp, from, to, dish - 1);
        }
    }

    /**
     * <p>非递归实现</p> 不会
     */
    public static void hanoi() {
    }

    public static void main(String[] args) {
        Stack<Integer> from = new Stack<>();
        from.push(5);
        from.push(4);
        from.push(3);
        from.push(2);
        from.push(1);
        Stack<Integer> temp = new Stack<>();
        Stack<Integer> to = new Stack<>();
        hanoi_recursion(from, temp, to, 5);
        int size = to.size();
        for (int i = 0; i < size; i++) {
            System.out.println(to.pop());
        }
    }
}