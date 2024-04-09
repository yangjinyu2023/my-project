package com.example.demo.designPattern.factory;

import java.util.Arrays;

public class FactoryDemo {
    // 工厂模式：简单工厂模式、工厂方法模式、抽象工厂模式
    //创建对象时，可能加载配置文件，可能需要关联其他对象等，如果每次创建对象都new，代码冗余，可读性差。举个例子，创建一个车的实例。

    //简单工厂模式：创建一个车工厂，创建车实例时传入类型，宝马、奔驰，返回对应的车实例。
    //如果加一个类型，车工厂就需要加一段if-else逻辑，不符合开闭原则。

    //工厂方法模式：每种类型车对应一个工厂，宝马工厂、奔驰工厂，想要不同的车实例，
    // 就用不同的车工厂去创建。如果加一个类型，就要新增一种工厂，这样工厂类越来越多，系统变复杂。

    //抽象工厂模式：把产品按类型分组，组内不同产品对应同一个工厂的不同方法。
    // 比如宝马、奔驰都分为轿车、SUV。那么创建一个轿车工厂，一个SUV工厂，奔驰、宝马对应不同的方法。
    public static void main(String[] args) {
        // 简单工厂
        CarFactory factory = new CarFactory();
        System.out.println(Arrays.asList(factory.getCar("BMW"), factory.getCar("BENZ")).toString());
        // 工厂方法
        BMWCarFactory bmwCarFactory = new BMWCarFactory();
        BENZCarFactory benzCarFactory = new BENZCarFactory();
        System.out.println(Arrays.asList(bmwCarFactory.getCar(), benzCarFactory.getCar()).toString());
        // 抽象工厂
        SaloonFactory saloonFactory = new SaloonFactory();
        SUVFactory suvFactory = new SUVFactory();
        System.out.println(Arrays.asList(saloonFactory.getBMW(), saloonFactory.getBENZ(), suvFactory.getBMW(), suvFactory.getBENZ()).toString());
    }
}