package com.example.demo.designPattern.factory;

public class SaloonFactory implements CarAbstarctFactory{
    @Override
    public Car getBENZ() {
        return new BENZSaloon();
    }

    @Override
    public Car getBMW() {
        return new BMWSaloon();
    }
}
