package org.example.model;

import org.example.controller.MyException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BiggerDirectory {
    private Set<Directory> directories;

    public BiggerDirectory() {
        directories = new HashSet<Directory>();
    }

    //Добавление нового отношения в базу знаний
    public void addUnits(String biggerUnitName, String smallerUnitName, BigDecimal value) throws MyException {
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
            } else {
                throw new MyException("Отношение "+biggerUnitName+" и "+smallerUnitName+" уже есть в справочнике. оно не будет добавлено\n");
            }
        }
    }

    //Связать справочники если дано выражение которое может соединить известные отношения
    private void mergeDirectories(Directory directoryWithBiggerUnit, Directory directoryWithSmallerUnit, String biggerUnitName, String smallerUnitName, BigDecimal value) {
        BigDecimal biggerV = null;
        BigDecimal smallerV = null;
        try {
            biggerV = directoryWithBiggerUnit.getValueByName(biggerUnitName);
            smallerV = directoryWithSmallerUnit.getValueByName(smallerUnitName);
        } catch (MyException e) {
            e.printStackTrace();
        }
        List<Unit> deletedList = directoryWithSmallerUnit.getUnitsList();
        Iterator<Unit> iterator = deletedList.iterator();
        while (iterator.hasNext()) {
            Unit unit = iterator.next();
            BigDecimal variable = unit.getValue();
            //unit.setValue((variable * biggerV) / (value * smallerV));
            unit.setValue(variable.multiply(biggerV).divide(value).divide(smallerV));
            directoryWithBiggerUnit.addUnit(unit);
        }
        directories.remove(directoryWithSmallerUnit);
    }

    //Найти ответ
    public BigDecimal getRatio(String knownUnit, String unkownUnit, BigDecimal value) throws MyException {
        Directory directoryWithBothUnits = null;
        Iterator<Directory> iterator = directories.iterator();
        while (iterator.hasNext()) {
            Directory dir = iterator.next();
            if(dir.hasInDirectory(knownUnit)&&dir.hasInDirectory(unkownUnit)){
                directoryWithBothUnits = dir;
            }
        }
        if(directoryWithBothUnits!=null){
            //отношение двух единиц из справочника умноженное на значение в введенной строки
            BigDecimal valKnown = directoryWithBothUnits.getValueByName(knownUnit);
            BigDecimal valUnknown = directoryWithBothUnits.getValueByName(unkownUnit);
                //return (valKnown*value)/(valUnknown);
            return valKnown.multiply(value).divide(valUnknown);

        }
        else {
            throw new MyException("Концертация невозможна\n");
        }
    }
}

