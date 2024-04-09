package com.example.demo.datastructure.recursion;

/**
 * 递归
 *
 * @author yangjinyu
 * @time 2021/6/9 20:02
 */
public class Recursion {
    /**
     * n的阶乘
     */
    public static int getFactorial(int n) {
        if (n < 0) {
            return -1;
        }
        // 边界条件
        if (n == 0) {
            // 当边界条件满足时，递归返回
            System.out.println(n + "!=1");
            return 1;
        } else {
            // 当边界条件不满足时，递归前进
            int temp = n * getFactorial(n - 1);
            System.out.println(n + "!=" + temp);
            return temp;
        }
    }

    // 如果如果求28次方，我们可以先假定22=a,于是28 = （22）4 ，那么就是a4 ；
    // 假定 a2 = b，那么 a4 = b2，而b2可以写成(b2)1。
    // 于是现在28就转换成：b*b，也就是说我们将乘方的运算转换为乘法的运算。
    // 求xy的值，当y是偶数的时候，最后能转换成两个数相乘，当时当y是奇数的时候，
    // 最后我们必须要在返回值后面额外的乘以一个x。
    // x^y= (x^2)^(y/2)，定义a=x^2,b=y/2, 则得到形如： x^y= a^b;
    public static int pow(int x, int y) {
        if (x == 0 || x == 1) {
            return x;
        }
        if (y > 1) {
            int b = y / 2;
            int a = x * x;
            if (y % 2 == 1) {//y为奇数
                return pow(a, b) * x;
            } else {//y为偶数
                return pow(a, b);
            }
        } else if (y == 0) {
            return 1;
        } else {//y==1
            return x;
        }
    }

    public static void main(String[] args) {
        getFactorial(5);
        System.out.println(pow(2, 6));
    }
}