package com.example.demo.designPattern.factory;

public class SUVFactory implements CarAbstarctFactory{
    @Override
    public Car getBENZ() {
        return new BENZSUV();
    }

    @Override
    public Car getBMW() {
        return new BMWSUV();
    }
}
