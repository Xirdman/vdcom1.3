package org.example.controller;

import org.example.model.BiggerDirectory;


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
        double stringDouble1 = Double.parseDouble(parsedSingleString[0]);
        String name1 = parsedSingleString[1];
        double stringDouble2 = Double.parseDouble(parsedSingleString[3]);
        String name2 = parsedSingleString[4];
        if (stringDouble1 == 0 || stringDouble2 == 0) {
            throw new MyException("Коэффициент перед числом не может быть равен нулю");
        }
        if (stringDouble1 < stringDouble2) {
            biggerDirectory.addUnits(name1, name2, stringDouble2 / stringDouble1);
        } else {
            biggerDirectory.addUnits(name2, name1, stringDouble1 / stringDouble2);
        }
    }

    public double getSingleAnswer(String singleString) throws MyException {
        String parsedString[] = singleString.split(" ");
        double variable = Double.parseDouble(parsedString[0]);
        if (variable == 0) {
            throw new MyException("Коэффициент перед числом не может быть равен нулю");
        }
        String knownUnitName = parsedString[1];
        String unknownUnitName = parsedString[4];
        return biggerDirectory.getRatio(knownUnitName, unknownUnitName, variable);
    }
}
