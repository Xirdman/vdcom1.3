package org.example;

import static org.junit.Assert.assertTrue;

import org.example.controller.Controller;
import org.example.model.BiggerDirectory;
import org.example.model.Unit;
import org.junit.Assert;
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
    }
    @Test
    public void testingMerge(){
        BiggerDirectory testDir = new BiggerDirectory();
        Unit testUnit1 = new Unit("milimeter", 1);
        Unit testUnit2 = new Unit("santimeter", 10);
        Unit testUnit3 = new Unit("decimeter", 100);
        Unit testUnit4 = new Unit("kilometer", 1000000);
        testDir.addUnits(testUnit2.getName(),
                testUnit1.getName(),
                testUnit2.getValue()/testUnit1.getValue());
        testDir.addUnits(testUnit3.getName(),
                testUnit2.getName(),
                testUnit3.getValue()/testUnit2.getValue());
        testDir.addUnits(testUnit4.getName(),
                testUnit3.getName(),
                testUnit4.getValue()/testUnit3.getValue());

        testDir.addUnits("meter","proxydecimeter",10);
        testDir.addUnits("kilometer","meter",1000);
    }
    @Test
    public void addFromText(){
        Controller.addManyStrings("1024 byte = 1 kilobyte \n" +
                "2 bar = 12 ring \n" +
                "16.8 ring = 2 pyramid \n" +
                "4 hare = 1 cat \n" +
                "5 cat = 0.5 giraffe \n" +
                "1 byte = 8 bit \n" +
                "15 ring = 2.5 bar\n");

    }
}
