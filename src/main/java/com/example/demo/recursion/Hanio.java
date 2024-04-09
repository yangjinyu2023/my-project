package com.example.demo.recursion;

/**
 * @description: 递归实现汉诺塔
 * 解决n块盘子从A移动到C，那么我只需要先把n-1块盘子从A移到B，
 * 然后把最下面的第n块盘子从A移到C，最后把n-1块盘子从B移到C。
 * 那么如何把n-1块盘子从A移到B？递归！
 * @author: yangjinyu
 * @time: 2020/9/23 10:54
 */
public class Hanio {

    public static void main(String[] args) {
        PZ pz = new PZ("1");
        System.out.println(pz.getName());
    }
}

class PZ {
    private String no;

    public PZ(String no) {
        this.no = no;
    }

    public String getName() {
        return "盘子" + no;
    }
}

class ZZ {
    private String no;

    public ZZ(String no) {
        this.no = no;
    }

    public String getName() {
        return "柱子" + no;
    }
}