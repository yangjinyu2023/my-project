package com.example.demo.designPattern.adapter;

public class AdapterClient {
    //Target(目标接口)：所需要转换的所期待的接口 ->SDCard
    //Adaptee(源角色)：需要适配的接口 ->TFCard
    //Adapter(适配器)：将原接口适配成目标接口，继承原接口，实现目标接口 ->SDAdapterTF
    public static void main(String[] args) {
        Computer computer = new ThinkpadComputer();
        SDCard sdCard = new SDCardImpl();
        System.out.println(computer.readSD(sdCard));
        // 希望在不改变计算机读取SD卡接口的情况下，通过适配器模式读取TF卡
        // 对于computer，只认识SDCard，因此适配器需要implement SDCard
        // 又要可以读取TF卡，因此需要将要适配的对象TFCard作为适配器的属性引入
        // 在readSD方法中调用TFCard.readSD()达到读取TF卡的目的
        System.out.println("====================================");
        TFCard tfCard = new TFCardImpl();
        SDCard tfCardAdapterSD = new SDAdapterTF(tfCard);
        System.out.println(computer.readSD(tfCardAdapterSD));
    }
}