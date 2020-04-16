package org.example.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BiggerDirectory {
    private  Set<Directory> directories = new HashSet<>();

    public BiggerDirectory() {
        directories = new HashSet<Directory>();
    }

    public void addUnits(String biggerUnitName, String smallerUnitName, double value) {
        Directory directoryOfBig = null;
        Directory directoryOfSmall = null;
        Iterator<Directory> iterator = directories.iterator();
        while (iterator.hasNext()) {
            Directory directoryToSeacrh = iterator.next();
            if (directoryToSeacrh.hasInDirectory(biggerUnitName)) {
                directoryOfBig =directoryToSeacrh;
            }
            if (directoryToSeacrh.hasInDirectory(smallerUnitName)) {
                directoryOfSmall = directoryToSeacrh;
            }
        }
        //Когда нет ни в одной директории
        if((directoryOfBig == null)&&(directoryOfSmall==null)){
            directories.add(new Directory(biggerUnitName,smallerUnitName,value));
        }
        //Когда один есть в одной директории, а второго нет нигде
        if((directoryOfBig == null)&&(directoryOfSmall!=null)){
            directoryOfSmall.addToSmallerExisting(smallerUnitName,biggerUnitName,value);
        }
        if((directoryOfBig!=null)&&(directoryOfSmall==null)){
            directoryOfBig.addToBiggerExisting(biggerUnitName,smallerUnitName,value);
        }
        //Когда оба есть в одной директории НИЧЕГО НЕ ПРОИСХОДИТ
        //Когда оба есть в разных директориях, надо связать
        if((directoryOfBig!=null)&&(directoryOfSmall!=null)){
            mergeDirectories(directoryOfBig,directoryOfSmall,biggerUnitName,smallerUnitName,value);
        }

        boolean flag = false;

    }
    private void mergeDirectories(Directory directoryWithBiggerUnit,Directory directoryWithSmallerUnit, String biggerUnitName,String smallerUnitName, double value){

    }
}
