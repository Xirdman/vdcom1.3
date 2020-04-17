package org.example.model;

import java.math.BigDecimal;

public class Unit {
    private String name;
    private double value;

    public String getName() {
        return this.name;
    }

    public Unit(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
