package com.example.demo.designPattern.command;

public class DrinkCommand implements ICommand{
    private Bar bar = new Bar();

    @Override
    public void execute() {
        bar.action();
    }
}