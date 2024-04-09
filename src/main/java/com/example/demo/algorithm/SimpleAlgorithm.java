package com.example.demo.algorithm;

import org.junit.Test;

/**
 * 简单算法
 *
 * @author yangjinyu
 * @time 2021/6/22 16:19
 */
public class SimpleAlgorithm {

    @Test
    public void printPrime() {
        printPrime1(23);
        printPrime2(17);
    }

    /**
     * 常规方式，打印素数（质数）
     * 对正整数n，如果用2到"根号n"之间的所有整数去除，均无法整除，则n为质数
     */
    private void printPrime1(int num) {
        boolean[] isPrimes = new boolean[num + 1];
        // 1、2为素数
        for (int i = 2; i < isPrimes.length; i++) {
            isPrimes[i] = true;
        }
        // 从3开始，用i整除2到根号i之间的整数，如果能整除说明不是素数
        for (int i = 3; i <= num; i++) {
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrimes[i] = false;
                    break;
                }
            }
        }

        System.out.print("质数有: ");
        for (int i = 2; i < isPrimes.length; i++) {
            if (isPrimes[i]) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * 埃氏筛选法，打印素数（质数）
     */
    private void printPrime2(int num) {
        boolean[] isPrimes = new boolean[num + 1];
        for (int i = 2; i < isPrimes.length; i++) {
            isPrimes[i] = true;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (isPrimes[i]) {
                for (int j = 2; i * j <= num; j++) {
                    isPrimes[i * j] = false;
                }
            }
        }

        System.out.print("质数有: ");
        for (int i = 2; i < isPrimes.length; i++) {
            if (isPrimes[i]) {
                System.out.print(i + " ");
            }
        }
    }


    @Test
    public void printGreatestCommonFactor() {
        System.out.println(getGCF(24, 40));
        System.out.println(getGCF_recursion(24, 40));
    }

    /**
     * 获取最大公约数（最大公因数）
     */
    private static int getGCF(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (max % min != 0) {
            int max_temp = Math.max(min, max % min);
            min = Math.min(min, max % min);
            max = max_temp;
        }

        return min;
    }

    /**
     * 获取最大公约数（最大公因数）
     */
    private static int getGCF_recursion(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        if (max % min != 0) {
            return getGCF_recursion(min, max % min);
        } else {
            return min;
        }
    }

    public static void main(String[] args) {
        System.out.println(getGCF_recursion(1024,521));
    }
}