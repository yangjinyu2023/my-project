package com.example.demo.designPattern.factory;
// 简单汽车工厂
public class CarFactory {
    public Car getCar(String brand){
        if("BMW".equals(brand)){
            return new BMWCar();
        }
        if("BENZ".equals(brand)){
            return new BENZCar();
        }
        return null;
    }
}