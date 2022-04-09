package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream(args[0]);

        byte buffer[] = new byte[is.available()];
        is.read(buffer);
        is.close();
        Arrays.sort(buffer);

        List<String> list = new ArrayList<>();
        Set<String> set = new TreeSet<>();
        for (byte b : buffer) {
            list.add(String.valueOf((char) b));
            set.add(String.valueOf((char) b));
        }

        for (String s : set) {
            System.out.println(s + " " + Collections.frequency(list, s));
        }

    }
}
