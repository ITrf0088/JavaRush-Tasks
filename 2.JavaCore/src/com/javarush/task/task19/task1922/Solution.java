package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String filename = bf.readLine();
        bf.close();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String string[] ;
        while (reader.ready()) {
            String s = reader.readLine();
            string = s.split(" ");
            int count = 0;
            for (int i = 0; i < words.size(); i++) {
                count += Collections.frequency(new ArrayList<>(Arrays.asList(string)), words.get(i));
            }
             if(count==2)System.out.println(s);
        }
        reader.close();
    }
}

