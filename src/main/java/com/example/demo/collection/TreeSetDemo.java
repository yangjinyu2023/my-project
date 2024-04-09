package com.example.demo.collection;

import com.example.demo.domain.Customer;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/2/2 19:52
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        Customer customer1 = new Customer(26, "yjy");
        Customer customer2 = new Customer(27, "yjy");
        Customer customer3 = new Customer(25, "yjy");
        Customer customer4 = new Customer(27, "yjy");

        // treeSet会走compareTo()中的逻辑
        TreeSet<Customer> treeSet = new TreeSet<>();
        treeSet.add(customer1);
        treeSet.add(customer2);
        treeSet.add(customer3);
        treeSet.add(customer4);

        // Comparator中的逻辑会覆盖compareTo()中的逻辑
        TreeSet<Customer> treeSet1 = new TreeSet<>((a, b) -> {
            if(a.getAge() > b.getAge())
                return 1;
            if(a.getAge() < b.getAge())
                return -1;
            return 0;
        });
        treeSet1.add(customer1);
        treeSet1.add(customer2);
        treeSet1.add(customer3);

        // hashSet排序假象 https://blog.csdn.net/zcc7up/article/details/59066267
        HashSet<Customer> hashSet = new HashSet<>();
        hashSet.add(customer1);
        hashSet.add(customer2);
        hashSet.add(customer3);

        for (Customer c : treeSet) {
            System.out.println(c.toString());
        }
        for (Customer c : treeSet1) {
            System.out.println(c.toString());
        }
        for (Customer c : hashSet) {
            System.out.println(c.toString());
        }
    }
}