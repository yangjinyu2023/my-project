package com.example.demo.jvm.reference;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        // obj1被强引用持有
        NormalObject object = new NormalObject("obj1");
        // obj2被弱引用持有
        WeakReference<NormalObject> reference = new WeakReference<>(new NormalObject("obj2"));
        System.out.println(object);//obj1
        System.out.println(reference.get());//obj2
        System.gc();
        Thread.sleep(1000);
        System.out.println(object);//obj1
        System.out.println(reference.get());//null
    }
}