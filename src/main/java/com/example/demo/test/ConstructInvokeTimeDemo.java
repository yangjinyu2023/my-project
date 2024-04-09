package com.example.demo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructInvokeTimeDemo {

    // 结论
    // 如果父类没有带参构造函数，子类默认调父类的无参构造函数
    // 否则子类必须实现父类的带参构造函数
    public static void main(String[] args) {
        new ConstructInvokeTime("s");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("1","1");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("2","2");

        List<Map<String, Object>> list1 = new ArrayList<>();
        list1.add(map1);

        List<Map<String, Object>> list2 = new ArrayList<>();
        list2.add(map2);

        list1 = list2;
        System.out.println(list1);

    }
}