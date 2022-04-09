package com.javarush.task.task34.task3412;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/* 
Добавление логирования в класс
*/

public class Solution {

     static class TestClass {
        private StringBuffer data;
        public TestClass() {
            this.data = new StringBuffer();
            for (long i = 0; i < 50000000; i++) {
                this.data.append('x');
            }
            System.out.println(data.toString());
        }
        @Override
        protected void finalize() {
            System.out.println("У объекта TestClass вызван метод finalize!!!");
        }
    }
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        logger.debug("debug debug");

        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public static void main(String[] args) {
            new TestClass();
    }

    public void calculateAndSetValue3(long value) {
        logger.trace("trace");

        value -= 133;
        if (value > Integer.MAX_VALUE) {
            value1 = (int) (value / Integer.MAX_VALUE);
            logger.debug("debug debug");
        } else {
            value1 = (int) value;
            logger.debug("debug debug");
        }
    }

    public void printString() {
        logger.trace("trace");

        if (value2 != null) {
            System.out.println(value2.length());
        }
    }

    public void printDateAsLong() {
        logger.trace("trace");
        if (value3 != null) {
            System.out.println(value3.getTime());
        }
    }

    public void divide(int number1, int number2) {
        logger.trace("trace");
        try {
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
            logger.error("Ошибка ошибка ошибка");
        }
    }

    public void setValue1(int value1) {
        logger.debug("debug debug");

        this.value1 = value1;
    }

    public void setValue2(String value2) {
        logger.debug("debug debug");

        this.value2 = value2;
    }

    public void setValue3(Date value3) {

        logger.debug("debug debug");
        this.value3 = value3;
    }
}
