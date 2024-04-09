package com.example.demo.domain;

import lombok.Data;

@Data
public class Customer implements Comparable<Customer> {

    public Customer(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private int age;

    private String name;

    @Override
    public int compareTo(Customer o) {
        // 先比较name
        if (this.name.compareTo(o.getName()) > 0)
            return 1;
        if (this.name.compareTo(o.getName()) < 0)
            return -1;

        // name相同，再比较age
        if (this.age > o.getAge()) {
            return -1;
        }
        if (this.age < o.getAge()) {
            return 1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (!(obj instanceof Customer))
            return false;
        final Customer other = (Customer) obj;

        if (this.name.equals(other.getName()) && this.age == other.getAge())
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        int result;
        int prime = 29;
        result = (name == null ? 0 : name.hashCode());
        result = prime * result + age;

        return result;
    }

    @Override
    public String toString() {
        return "name " + name + " age " + age;
    }
}