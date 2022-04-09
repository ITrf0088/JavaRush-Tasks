package com.javarush.task.task19.task1911;

/* 
Ридер обертка
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
       PrintStream printStream = new PrintStream(bytes);

       System.setOut(printStream);
       testString.printSomething();
       String string = bytes.toString();
       System.setOut(consoleStream);
        System.out.println(string.replace("te","??"));
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
