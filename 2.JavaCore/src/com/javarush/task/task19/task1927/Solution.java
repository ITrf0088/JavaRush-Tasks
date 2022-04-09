package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        String str = "JavaRush - курсы Java онлайн";
        PrintStream printStream = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);

        System.setOut(ps);
        testString.printSomething();
        System.setOut(printStream);
        String [] s = baos.toString().split("\n");

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
            if(i%2 !=0) System.out.println(str);
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
