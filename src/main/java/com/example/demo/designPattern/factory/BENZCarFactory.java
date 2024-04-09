package com.example.demo.designPattern.factory;

public class BENZCarFactory implements CarFactoryInterface {
    @Override
    public Car getCar() {
        return new BENZCar();
    }
}