package com.example.demo.designPattern.chain;

public abstract class ChainHandler {

    public void execute(Chain chain){
        handleProcess();
        chain.proceed();
    }

    protected abstract void handleProcess();
}