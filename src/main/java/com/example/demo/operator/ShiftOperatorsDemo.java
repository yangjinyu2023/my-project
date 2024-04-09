package com.example.demo.operator;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/2/4 18:02
 */
public class ShiftOperatorsDemo {
    public static void main(String[] args) {
        // 正数：原码=反码=补码
        // 负数：反码=原码取反 补码=反码+1
        // 5的原码取反+1得到-5
        // 5 -> 00000000,00000000,00000000,00000101
        //-5 -> 11111111,11111111,11111111,11111011
        System.out.println(5 + " " + Integer.toBinaryString(5));
        System.out.println(-5 + " " + Integer.toBinaryString(-5));

        // <<	左移运算符，将运算符左边的对象向左移动运算符右边指定的位数（在低位补0）
        // >> "有符号"右移运算 符，将运算符左边的对象向右移动运算符右边指定的位数。
        //      使用符号扩展机制，也就是说，如果值为正，则在高位补0，如果值为负，则在高位补1.
        // >>>	"无符号"右移运算 符，将运算符左边的对象向右移动运算符右边指定的位数。
        //      采用0扩展机制，也就是说，无论值的正负，都在高位补0.
        System.out.println(6297 + " " + Integer.toBinaryString(6297));
        System.out.println(-6297 + " " + Integer.toBinaryString(-6297));
        System.out.println("6297>>5 " + Integer.toBinaryString(6297 >> 5));
        System.out.println("-6297>>5 " + Integer.toBinaryString(-6297 >> 5));
        System.out.println("6297>>>5 " + Integer.toBinaryString(6297 >>> 5));
        System.out.println("-6297>>>5 " + Integer.toBinaryString(-6297 >>> 5));
        System.out.println("6297<<5 " + Integer.toBinaryString(6297 << 5));
        System.out.println("-6297<<5 " + Integer.toBinaryString(-6297 << 5));
    }
}