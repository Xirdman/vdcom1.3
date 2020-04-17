package org.example.model;

import java.math.BigDecimal;

public class Unit {
    private String name;
    private BigDecimal value;
    public String getName(){
        return this.name;
    }

    public Unit(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value){
        this.value = value;
    }
}
