package org.example.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BiggerDirectory {
    private Set<Directory> directories;

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
                directoryOfBig = directoryToSeacrh;
            }
            if (directoryToSeacrh.hasInDirectory(smallerUnitName)) {
                directoryOfSmall = directoryToSeacrh;
            }
        }
        //Когда нет ни в одной директории
        if ((directoryOfBig == null) && (directoryOfSmall == null)) {
            directories.add(new Directory(biggerUnitName, smallerUnitName, value));
        }
        //Когда один есть в одной директории, а второго нет нигде
        if ((directoryOfBig == null) && (directoryOfSmall != null)) {
            directoryOfSmall.addToSmallerExisting(smallerUnitName, biggerUnitName, value);
        }
        if ((directoryOfBig != null) && (directoryOfSmall == null)) {
            directoryOfBig.addToBiggerExisting(biggerUnitName, smallerUnitName, value);
        }
        //Когда оба есть в одной директории НИЧЕГО НЕ ПРОИСХОДИТ
        //Когда оба есть в разных директориях, надо связать
        if ((directoryOfBig != null) && (directoryOfSmall != null)) {
            if (directoryOfBig != directoryOfSmall) {
                mergeDirectories(directoryOfBig, directoryOfSmall, biggerUnitName, smallerUnitName, value);
            }
            else {
                System.out.print("Отношение для "+ biggerUnitName +" и "+ smallerUnitName+" уже определены,и не будут добавлены");
            }
        }
    }

    private void mergeDirectories(Directory directoryWithBiggerUnit, Directory directoryWithSmallerUnit, String biggerUnitName, String smallerUnitName, double value) {
        double biggerV = directoryWithBiggerUnit.getValueByName(biggerUnitName);
        double smallerV = directoryWithSmallerUnit.getValueByName(smallerUnitName);
        List<Unit> deletedList = directoryWithSmallerUnit.getUnitsList();
        Iterator<Unit> iterator = deletedList.iterator();
        while (iterator.hasNext()) {
            Unit unit = iterator.next();
            double variable = unit.getValue();
            unit.setValue((variable * biggerV) / (value * smallerV));
            directoryWithBiggerUnit.addUnit(unit);
        }
        directories.remove(directoryWithSmallerUnit);
    }
}

