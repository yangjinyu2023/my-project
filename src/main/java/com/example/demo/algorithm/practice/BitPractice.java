package com.example.demo.algorithm.practice;

import org.junit.Assert;
import org.junit.Test;

/**
 * 位运算练习
 *
 * @author yangjinyu
 * @time 2022/10/25 22:55
 */
public class BitPractice {
    // 剑指offer-56-I
    // 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
    // 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
    public int[] singleNumbers(int[] nums) {
        // 如果a=b，那么a^b=0
        // 整型数组 nums 里除一个数字之外，其他数字都出现了两次，这种直接异或，得到结果
        // 本题说除了两个数字x、y外，其他数字都出现两次，因此需要拆分成两个数组，一个包含x，一个包含y
        int tmp = 0;
        for (int num : nums) {
            tmp = tmp ^ num;
        }
        int offset = 1;
        while ((tmp & offset) == 0){
            offset = offset << 1;
        }
        // 假设x=3、y=5，二级制0011、0101，异或后变成0110
        // 从低位到高位找到第一个为1的位置，x和y在该位置上一定一个为1一个为0
        // 通过该位置可以将数组分成两份（num&0010是否为0）
        int x = 0, y = 0;
        for (int num : nums) {
            if((num & offset) == 0){
                x = x ^ num;
            }else{
                y = y ^ num;
            }
        }
        return new int[]{x,y};
    }

    // 剑指offer-65-II
    // 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
    public int singleNumber(int[] nums) {
        // 按位相加，对3取余，如果不是0，代表该位为1
        int[] counts = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                counts[i] += num & 1;
                num = num >>> 1;// 无符号右移。有符号右移，高位会补符号位
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = res << 1;
            res = res | (counts[i] % 3);
        }
        return res;
    }

    // 剑指offer-65
    // 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
    public int add(int a, int b) {
        while(a != 0){
            int c = (a&b) << 1;
            int d = a ^ b;
            a = c;
            b = d;
        }
        return b;
    }

    @Test
    public void test(){
        int a = 10, b = 88;
        Assert.assertEquals(a+b,add(a,b));
    }
}