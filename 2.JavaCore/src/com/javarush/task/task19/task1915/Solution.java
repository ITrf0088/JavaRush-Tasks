package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String file  = bf.readLine();
        bf.close();

        PrintStream printStream = System.out;

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream printStream1 = new PrintStream(bytes);

        System.setOut(printStream1);
        testString.printSomething();


        System.setOut(printStream);
        String string = bytes.toString();
        System.out.println(string);
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(string.getBytes());
        fo.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}



