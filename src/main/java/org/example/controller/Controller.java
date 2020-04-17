package org.example.controller;

import org.example.model.BiggerDirectory;

import java.math.BigDecimal;

public class Controller {
    private static BiggerDirectory biggerDirectory = new BiggerDirectory();

    public void addManyStrings(String richText) throws MyException {
        String array[] = richText.split("\n");
        for (String i : array) {
            try {
                addSingleString(i);
            } catch (MyException e) {
                throw e;
            }
        }

    }

    public String getManyAnswers(String richText) throws MyException {
        String result = "";
        String array[] = richText.split("\n");
        for (String i : array
        ) {
            try {
                result += getSingleAnswer(i);
            } catch (MyException e) {
                throw e;
            }
        }
        return result;
    }

    public void addSingleString(String singleString) throws MyException {
        String parsedSingleString[] = singleString.split(" ");
        BigDecimal stringDouble1 = BigDecimal.valueOf(Double.parseDouble(parsedSingleString[0]));
        String name1 = parsedSingleString[1];
        BigDecimal stringDouble2 = BigDecimal.valueOf(Double.parseDouble(parsedSingleString[3]));
        String name2 = parsedSingleString[4];
        if (stringDouble1.equals(new BigDecimal(0)) || stringDouble2.equals(new BigDecimal(0)) ) {
            throw new MyException("Коэффициент перед числом не может быть равен нулю");
        }
        if (stringDouble1.compareTo(stringDouble2)== -1) {
            //biggerDirectory.addUnits(name1, name2, stringDouble2 / stringDouble1);
            biggerDirectory.addUnits(name1,name2, stringDouble2.divide(stringDouble1));
        } else {
            biggerDirectory.addUnits(name2, name1, stringDouble1.divide(stringDouble2));
        }
    }

    public BigDecimal getSingleAnswer(String singleString) throws MyException {
        String parsedString[] = singleString.split(" ");
        BigDecimal variable = BigDecimal.valueOf(Double.parseDouble(parsedString[0]));
        if (variable.equals(new BigDecimal(0))) {
            throw new MyException("Коэффициент перед числом не может быть равен нулю");
        }
        String knownUnitName = parsedString[1];
        String unknownUnitName = parsedString[4];
        return biggerDirectory.getRatio(knownUnitName, unknownUnitName, variable);
    }
}
