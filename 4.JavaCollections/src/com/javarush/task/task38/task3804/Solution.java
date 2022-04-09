package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {

        return FactoryException.class;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        System.out.println(FactoryException.getThrowable(null));
    }
}