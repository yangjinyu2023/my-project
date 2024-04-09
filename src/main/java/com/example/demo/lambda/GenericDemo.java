package com.example.demo.lambda;

import java.util.GregorianCalendar;

class GenericDemo<T extends Comparable<? super T>> {

    public static void main(String[] args) {
        GenericDemo<GregorianCalendar> p = null;
    }
}