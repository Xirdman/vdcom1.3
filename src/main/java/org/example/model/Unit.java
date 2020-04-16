package org.example.model;

public class Unit {
    private String name;
    private double value;
    public String getName(){
        return this.name;
    }

    public Unit(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}