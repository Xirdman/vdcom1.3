package org.example.model;

import org.example.controller.MyException;

import java.math.BigDecimal;
import java.util.*;

public class Directory {
    private List<Unit> list;
   // конструктор
    public Directory() {
        list = new LinkedList<Unit>();
    }
    //конструктор
    public Directory(String nameOfBigger,String nameOfSmaller, BigDecimal value){
        list = new LinkedList<Unit>();
        list.add(new Unit(nameOfSmaller,new BigDecimal(1.0)));
        list.add(new Unit(nameOfBigger,value));
    }
    //Возвращает true если есть единицы с таким именем в этой директории
    public boolean hasInDirectory(String unitName){
        Iterator<Unit> iterator = list.listIterator();
        while (iterator.hasNext()){
            if(iterator.next().getName().equals(unitName)){
                return true;
            }
        }
        return false;
    }
    //Добавление единицы, когда меньшее значение есть в директории
    public void addToSmallerExisting(String smallerUnitName,String newUnitName, BigDecimal newUnitValue){
        BigDecimal smallerUnitValue= null;
        try {
            smallerUnitValue = getValueByName(smallerUnitName);
        } catch (MyException e) {
            e.printStackTrace();
        }
        //list.add(new Unit(newUnitName, new BigDecimal ((newUnitValue).multiply(new BigDecimal(smallerUnitValue)));
        list.add(new Unit(newUnitName,newUnitValue.multiply(smallerUnitValue)));
    }
    public void addToBiggerExisting(String biggerUnitName, String newUnitName, BigDecimal newUnitValue){
        BigDecimal biggerUnitValue = null;
        try {
            biggerUnitValue = getValueByName(biggerUnitName);
        } catch (MyException e) {
            e.printStackTrace();
        }
        //list.add(new Unit(newUnitName,biggerUnitValue/newUnitValue));
        list.add(new Unit(newUnitName,biggerUnitValue.divide(newUnitValue)));

    }
    //Поиск значения по имени
    public BigDecimal getValueByName(String unitName) throws MyException {
        Iterator<Unit> iterator = list.listIterator();
        while (iterator.hasNext()){
            Unit next = iterator.next();
            if(next.getName().equals(unitName)){
                return next.getValue();
            }
        }
        throw new MyException(unitName+" не найден");
    }
    public int getLenght(){
        return list.size();
    }
    public List<Unit> getUnitsList(){
        return list;
    }
    public void addUnit(Unit unit){
        list.add(unit);
    }

}
