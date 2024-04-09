package com.example.demo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        // 创建list并赋值，要点：后面泛型要写上+两个大括号
        // 第一个花括号表示匿名内部类
        // 第二花括号表示匿名内部类的构造代码块，可以有多个
        // 构造代码块在构造函数前执行，匿名内部类的构造代码块在调用父类同参构造函数后执行
        List<Integer> list = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};
        // 1、List.toArray()
        // Integer[] arr1 = list.toArray(); 编译错误，List.toArray()返回的是Object[]
        // T[]，如果集合适合指定的数组，则返回其中。 否则，将为指定数组的运行时类型和此集合的大小分配一个新数组。
        Integer[] arr1 = list.toArray(new Integer[0]);// 正确
        List<Integer> list1 = Arrays.asList(arr1);
        // 2、int[]和List<Integer>的互转
        // int[] arr2 = list.toArray(new int[0]); 编译错误，int[] 不是 T[]
        int[] arr2 = list.stream().mapToInt(Integer::intValue).toArray();// 正确
        // 3、int[]和Integer[]的互转
        Integer[] arr3 = Arrays.stream(arr2).boxed().toArray(Integer[]::new);
        int[] arr4 = Arrays.stream(arr3).mapToInt(Integer::intValue).toArray();

        // 二维数组
        // 1、创建二维数组，第二个中括号可以不指定长度，动态分配
        // int[][] arr_2D = new int[][]; 编译错误，第一个中括号必须指定长度
        int[][] arr_2D = new int[1][];
        // 2、初始化二维数组，不需要在中括号指定长度（和一维数组一样）
        int[][] arr_2D_1 = new int[][]{{1, 2, 3}};
        // List<int[]> 和 int[][] 的互转
        List<int[]> list2 = new ArrayList<int[]>() {{
            add(new int[]{1, 2});
        }};
        int[][] arr_2D_2 = list2.toArray(new int[0][]);
        // 为啥上面这个行，int[] arr2 = list.toArray(new int[0]);就报错？【记住吧】

        // List<List<Integer>> 和 int[][] 的互转
        List<List<Integer>> list3 = new ArrayList<>();
        list3.add(list1);
        int[][] arr_2D_3 = list3.stream()
                .map(m -> m.stream().mapToInt(Integer::intValue).toArray())
                .toArray(m -> new int[list3.size()][]);
        // 上面这里就不能写new int[0][]，是stream要求传入个IntFunction
        // 有印象就好了，最实际写法还是循环赋值。
        // 但是list.stream().mapToInt(Integer::intValue).toArray()写法要记住！
        System.out.println("===========================");
    }
}