package com.example.demo.jvm.finalize;

public class Finalization {
    public static Finalization finalization;

    @Override
    protected void finalize(){
        System.out.println("finalize");
        finalization = this;
    }

    public static void main(String[] args) {
        Finalization f = new Finalization();
        System.out.println(f);
        f = null;
        System.gc();
        System.out.println(f);
        System.out.println(f.finalization);
    }
}