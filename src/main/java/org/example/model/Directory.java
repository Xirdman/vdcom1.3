package org.example.model;

import org.example.controller.MyException;

import java.util.*;

public class Directory {
    private List<Unit> list;

    public Directory() {
        list = new LinkedList<Unit>();
    }

    public Directory(String nameOfBigger, String nameOfSmaller, double value) {
        list = new LinkedList<Unit>();
        list.add(new Unit(nameOfSmaller, 1));
        list.add(new Unit(nameOfBigger, value));
    }

    //Возвращает true если есть единицы с таким именем в этой директории
    public boolean hasInDirectory(String unitName) {
        Iterator<Unit> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(unitName)) {
                return true;
            }
        }
        return false;
    }

    //Добавление единицы, когда меньшее значение есть в директории
    public void addToSmallerExisting(String smallerUnitName, String newUnitName, double newUnitValue) throws MyException {
        double smallerUnitValue = 0;
        try {
            smallerUnitValue = getValueByName(smallerUnitName);
        } catch (MyException e) {
            throw e;
        }
        list.add(new Unit(newUnitName, newUnitValue * smallerUnitValue));
    }

    public void addToBiggerExisting(String biggerUnitName, String newUnitName, double newUnitValue) throws MyException {
        double biggerUnitValue = 0;
        try {
            biggerUnitValue = getValueByName(biggerUnitName);
        } catch (MyException e) {
            throw e;
        }
        list.add(new Unit(newUnitName, biggerUnitValue / newUnitValue));

    }

    //Поиск значения по имени
    public double getValueByName(String unitName) throws MyException {
        Iterator<Unit> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Unit next = iterator.next();
            if (next.getName().equals(unitName)) {
                return next.getValue();
            }
        }
        throw new MyException(unitName + " не найден");
    }

    public int getLenght() {
        return list.size();
    }

    public List<Unit> getUnitsList() {
        return list;
    }

    public void addUnit(Unit unit) {
        list.add(unit);
    }

}
