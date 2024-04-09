package com.example.demo.designPattern.command;

public class EatCommand implements ICommand{
    private Restaurant restaurant = new Restaurant();
    @Override
    public void execute() {
        restaurant.action();
    }
}