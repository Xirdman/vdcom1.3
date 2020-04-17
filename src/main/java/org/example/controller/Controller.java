package org.example.controller;

import org.example.model.BiggerDirectory;

public class Controller {
    private static  BiggerDirectory directory = new BiggerDirectory();
    public static void addManyStrings(String richText){
        String array[] = richText.split("\n");
        for (String i:array) {
            addSingleString(i);
        }

    }
    public static String getManyAnswers(String richText){
        String result = "";
        String array[] = richText.split("\n");
        for (String i:array
             ) {
            result+=getSingleAnswer(i);
        }
        return result;
    }
    public static void addSingleString(String singleString){
        String parsedSingleString[] = singleString.split(" ");
        double stringDouble1 =Double.parseDouble( parsedSingleString[0]);
        String name1 = parsedSingleString[1];
        double stringDouble2 =Double.parseDouble( parsedSingleString[3]);
        String name2 = parsedSingleString[4];
        if(stringDouble1<stringDouble2){
            directory.addUnits(name1,name2,stringDouble2/stringDouble1);
        }
        else{
            directory.addUnits(name2,name1,stringDouble1/stringDouble2);
        }
    }
    public static String getSingleAnswer(String singleString){
        return "";
    }
}
