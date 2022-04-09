package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.*;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(bf.readLine()));
        bf.close();


        while (reader.ready()){
            System.out.println(new StringBuilder(reader.readLine()).reverse());
        }
        reader.close();
    }
}
