package com.example.demo.designPattern.factory;

public class BMWCarFactory implements CarFactoryInterface {
    @Override
    public Car getCar() {
        return new BMWCar();
    }
}