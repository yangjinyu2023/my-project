package com.example.demo.test;

import com.example.demo.utils.SerialCloneUtil;
import com.google.common.base.Objects;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QueryCondition implements Cloneable, Serializable {

    final int value1 = 1;

    final static int value2 = 2;

    public void printlnValue() {
        final int value3 = 3;
        final int value4 = getValue();
        final int value5;
        System.out.println(value1+value2+value3+value4);
    }

    private int getValue() {
        int m = 10;
        int n = 20;
        return m + n;
    }

    public QueryCondition() {
    }

    public QueryCondition(List<Long> person) {
        this.person = person;
    }

    private List<Long> person;

    private String name;

    @Override
    public QueryCondition clone() {
        QueryCondition queryCondition = null;
        try {
            queryCondition = SerialCloneUtil.deepClone(this);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return queryCondition;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(person, name);
    }
}