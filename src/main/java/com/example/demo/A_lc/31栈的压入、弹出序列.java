package com.example.demo.A_lc;
//输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈
//的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。 
//
// 
//
// 示例 1： 
//
// 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
//输出：true
//解释：我们可以按以下顺序执行：
//push(1), push(2), push(3), push(4), pop() -> 4,
//push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
// 
//
// 示例 2： 
//
// 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
//输出：false
//解释：1 不能在 2 之前弹出。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= pushed.length == popped.length <= 1000 
// 0 <= pushed[i], popped[i] < 1000 
// pushed 是 popped 的排列。 
// 
//
// 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/ 
// Related Topics 栈 数组 模拟 👍 352 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution31 {

    // 看这个解法
    public boolean isPopOrder(int[] pushOrder, int[] popOrder) {
        if (pushOrder == null || popOrder == null || pushOrder.length != popOrder.length) {
            return false;
        }

        int pushIndex = 0; // 压栈序列的索引
        int popIndex = 0;  // 弹出序列的索引
        Stack<Integer> stack = new Stack<>(); // 辅助栈

        while (popIndex < popOrder.length) {
            // 如果栈不为空且栈顶元素等于当前需要弹出的元素，则弹出
            if (!stack.isEmpty() && stack.peek() == popOrder[popIndex]) {
                stack.pop();
                popIndex++;
            }
            // 栈顶元素不符合弹出条件或者栈为空，则将压栈序列的元素压入栈
            else if (pushIndex < pushOrder.length) {
                stack.push(pushOrder[pushIndex]);
                pushIndex++;
            }
            // 如果以上条件都不满足，说明弹出序列不合法
            else {
                return false;
            }
        }

        // 所有元素都正确处理完毕，说明弹出序列合法
        return true;
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 辅助栈模拟
        int length = popped.length;
        if (length == 0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int poppedPoint = 0;
        for (int num : pushed) {
            stack.push(num);
            // 并不需要加poppedPoint < length的条件，该条件always true
            while (!stack.isEmpty()
                    && stack.peek() == popped[poppedPoint]
                    && poppedPoint < length) {
                stack.pop();
                poppedPoint++;
            }
        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
