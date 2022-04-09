package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Arrays;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader==null) return "";
        String s = "";
        char[] chars = new char[1024];
        int len = reader.read(chars);
        for (int i = 0; i < len; i++) {
            s += ((char)(chars[i] + key));
        }
        return s;
    }
}
