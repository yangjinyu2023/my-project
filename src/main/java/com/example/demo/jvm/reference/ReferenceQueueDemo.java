package com.example.demo.jvm.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ReferenceQueueDemo {
    public static ReferenceQueue<NormalObject> rq = new ReferenceQueue<>();

    public static void checkQueue() {
        Reference<NormalObject> reference;
        while ((reference = (Reference<NormalObject>) rq.poll()) != null) {
            // 打印引用的名字
            System.out.println("In queue" + ((NormalObjectWeakReference) reference).name);
            // 打印真正引用的对象的名字
            System.out.println("Reference Object: " + reference.get());
        }
    }

    public static void main(String[] args) {
        List<WeakReference<NormalObject>> weakReferenceList = new ArrayList<>();
        // 添加三个弱引用
        for (int i = 0; i < 3; i++) {
            WeakReference reference = new NormalObjectWeakReference(new NormalObject("object" + i), rq);
            weakReferenceList.add(reference);
            // 打印每个弱引用
            System.out.println("create object:" + weakReferenceList.get(i));
        }
        System.out.println("first time checkQueue");
        checkQueue();
        System.out.println("GC");
        System.gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("second time checkQueue");
        checkQueue();
    }
}