package com.example.demo.designPattern.adapter;

// 对于computer，只认识SDCard，因此适配器需要implement SDCard
// 又要可以读取TF卡，因此需要将要适配的对象TFCard作为适配器的属性引入
// 在readSD方法中调用TFCard.readSD()达到读取TF卡的目的
public class SDAdapterTF implements  SDCard{
    private TFCard tfCard;

    public SDAdapterTF(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        System.out.println("adapter read tf card ");
        return tfCard.readTF();
    }
    @Override
    public int writeSD(String msg) {
        System.out.println("adapter write tf card");
        return tfCard.writeTF(msg);
    }
}
