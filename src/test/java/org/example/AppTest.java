package org.example;

import static org.junit.Assert.assertTrue;

import org.example.model.BiggerDirectory;
import org.example.model.Unit;
import org.junit.Test;


public class AppTest {
    @Test
    public void addingUnits() {
        BiggerDirectory directory = new BiggerDirectory();
        Unit testUnit1 = new Unit("santimeter", 1);
        Unit testUnit2 = new Unit("decimeter", 10);
        Unit testUnit3 = new Unit("meter", 100);
        directory.addUnits(testUnit2.getName(),
                testUnit1.getName(),
                testUnit2.getValue()/testUnit1.getValue());
        directory.addUnits(testUnit3.getName(),
                testUnit2.getName(),
                testUnit3.getValue()/testUnit2.getValue());
        directory.addUnits("meter",
                "halfmeter",
                2);
        boolean flag = false;

    }
}
