package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream pr = System.out;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bo);
        System.setOut(ps);
        testString.printSomething();
        System.setOut(pr);

        String string = bo.toString();
        String[] s =  string.split("\\s\\D\\s");
        int first =Integer.parseInt(s[0]);
        int second =Integer.parseInt(s[1]);
        if(string.contains("+")){
            System.out.println(String.format("%d %s %d = %d", first, "+", second, first + second));
        }else if(string.contains("*")){
            System.out.println(String.format("%d %s %d = %d", first, "*", second, first * second));
        }if(string.contains("-")){
            System.out.println(String.format("%d %s %d = %d", first, "-", second, first - second));
        }




    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

