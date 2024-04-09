package com.example.demo.test;

/**
 * @author yangjinyu
 * @time 2023/11/15 10:05
 */
public class TT3 {

    public void do1(TT2 tt2) {
        System.out.println(tt2.getPredicate().test(null));
    }

    public boolean do2() {
        return false;
    }
}