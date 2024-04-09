package com.example.demo.designPattern.adapter;

public class SDCardImpl implements SDCard {
    @Override
    public String readSD() {
        return "sdcard read a msg :hello word SD";
    }
    @Override
    public int writeSD(String msg) {
        System.out.println("sd card write msg : " + msg);
        return 1;
    }
}