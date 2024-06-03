package com.example.demo.A_lc;//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
//
// 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。
// 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
//
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2],..., a[n-2]] 。
//
// 
//
// 示例 1： 
//
// 
//输入：numbers = [3,4,5,1,2]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：numbers = [2,2,2,0,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// n == numbers.length 
// 1 <= n <= 5000 
// -5000 <= numbers[i] <= 5000 
// numbers 原来是一个升序排序的数组，并进行了 1 至 n 次旋转 
// 
//
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-
//sorted-array-ii/ 
// Related Topics 数组 二分查找 👍 650 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution11 {
    public int minArray(int[] numbers) {
        // 看Solutions第一个，讲解的很明白
        // 要点：二分法，用mid和end比较确定在左半部分还是右半部分
        // 时间复杂度O(logN)，空间复杂度0(1)
        int s = 0, e = numbers.length - 1;
        // 这里用while(s < e)和while(s <= e)都行
        // 考虑边界，数组长度为1，s==e==m==0，numbers[m] == numbers[e]，e--后s>e，可以跳出循环
        // 数组长度为2，
        // （1）假设为numbers为[0,7]，那么s=0,m=0,e=1,numbers[m]<numbers[e],e=m==0,e==s，继续循环e--，s>e可以跳出循环
        // （2）假设numbers为[7,0]，那么s=0,m=0,e=1,numbers[m]>numbers[e],s=m+1==1,e==s，继续循环e--，s>e可以跳出循环
        while (s < e) {
            int m = s + (e - s) / 2;
            if (numbers[m] > numbers[e]) {
                // 【关键点】必须是m+1而不是m（旋转点一定不大于尾点，因此可以跳过m）
                // 关键点理解：拿长度为2的数组思考
                // 假设numbers为[0,7]，那么s=0,m=0,e=1,numbers[m]<numbers[e],e=m==0,e==s成立跳出循环
                // 假设numbers为[7,0]，那么s=0,m=0,e=1,numbers[m]>numbers[e],s=m+1==1,e==s成立跳出循环，如果是s=m则死循环
                // 在只有两个元素的情况下，由于是向下取整，因此m==s，让e=m发生移动，让s=m未发生移动，因此必须s=m+1
                s = m + 1;
            } else if (numbers[m] < numbers[e]) {
                // numbers[m]<numbers[e]的情况，有可能m就是旋转点，不能跳过m（即不能e=m-1）
                e = m;
            } else {
                // 存在等于的情况，这里也是关键
                e--;
            }
        }
        // 为什么最后取s？因为执行了e--？
        return numbers[s];
    }
    // 由于向下取整，当low==mid==high-1的场景，让high = mid会造成high的移动，而不会导致死循环
    // 相反，不会出现low+1==mid==high的场景，因此让low = mid + 1才会造成low的移动，否则死循环
    // 换言之，mid更靠近low（在high-low=1时，mid等于low）
    // 边界值的分析：https://blog.csdn.net/qq_39602487/article/details/116594998
}
//leetcode submit region end(Prohibit modification and deletion)
