package org.example;

import static org.junit.Assert.assertTrue;

import org.example.controller.Controller;
import org.example.controller.MyException;
import org.example.model.BiggerDirectory;
import org.example.model.Unit;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;


public class AppTest {
    @Test
    public void addingUnits() {
        BiggerDirectory directory = new BiggerDirectory();
        Unit testUnit1 = new Unit("santimeter", 1);
        Unit testUnit2 = new Unit("decimeter", 10);
        Unit testUnit3 = new Unit("meter", 100);
        try {
            directory.addUnits(testUnit2.getName(),
                    testUnit1.getName(),
                    testUnit2.getValue()/ testUnit1.getValue());
            directory.addUnits(testUnit3.getName(),
                    testUnit2.getName(),
                    testUnit3.getValue()/testUnit2.getValue());
            directory.addUnits("meter",
                    "halfmeter",
                    2);
        } catch (MyException e) {
            System.out.print(e.getMessage());
        }
    }

    @Test
    public void testingMerge() {
        BiggerDirectory testDir = new BiggerDirectory();
        Unit testUnit1 = new Unit("milimeter",1);
        Unit testUnit2 = new Unit("santimeter", 10);
        Unit testUnit3 = new Unit("decimeter", 100);
        Unit testUnit4 = new Unit("kilometer", 1000000);
        try {
            testDir.addUnits(testUnit2.getName(),
                    testUnit1.getName(),
                    testUnit2.getValue()/testUnit1.getValue());
            testDir.addUnits(testUnit3.getName(),
                    testUnit2.getName(),
                    testUnit3.getValue()/testUnit2.getValue());
            testDir.addUnits(testUnit4.getName(),
                    testUnit3.getName(),
                    testUnit4.getValue()/testUnit3.getValue());

            testDir.addUnits("meter", "proxydecimeter", 10);
            testDir.addUnits("kilometer", "meter", 10);
        } catch (MyException e) {
            System.out.print(e.getMessage());
        }
    }

    @Test
    public void addFromText() {
        Controller con = new Controller();
        try {
            con.addManyStrings("1024 byte = 1 kilobyte \n" +
                    "2 bar = 12 ring \n" +
                    "16.8 ring = 2 pyramid \n" +
                    "4 hare = 1 cat \n" +
                    "5 cat = 0.5 giraffe \n" +
                    "1 byte = 8 bit \n" +
                    "15 ring = 2.5 bar\n");
        } catch (Exception e) {

        }

    }

    @Test
    public void testRightAnswerWithBytes() {
        BiggerDirectory bg = new BiggerDirectory();
        double testAnswer =0;
        try {
            bg.addUnits("byte", "bit", 8);
            testAnswer = bg.getRatio("bit", "byte", 16);
        } catch (MyException e) {
            System.out.print(e.getMessage());
        }
        double rightAnswer = 2;
        Assert.assertEquals(rightAnswer, testAnswer,0.01);
    }

    @Test
    public void testRightAnswerWithRings() {
        BiggerDirectory bg = new BiggerDirectory();
        double testAnswer =0;
        try {
            bg.addUnits("bar", "ring", 6);
            testAnswer = bg.getRatio("bar", "ring", 6);
        } catch (MyException e) {
            System.out.print(e.getMessage());
        }
        double rightAnswer = 36;
        Assert.assertEquals(rightAnswer, testAnswer, 0.01);
    }
    @Test
    public void testRightAnswerWithKylobytes() {
        BiggerDirectory bg = new BiggerDirectory();
        double testAnswer =0;
        try {
            //В одном байте 1024 килобайта
            bg.addUnits("kilobyte", "byte", 1024);
            //сколько килобайтов в 512 байтах
            testAnswer = bg.getRatio("byte", "kilobyte", 512);
        } catch (MyException e) {
            System.out.print(e.getMessage());
        }
        double rightAnswer = 0.5;
        Assert.assertEquals(rightAnswer, testAnswer,0.01);
    }
}
