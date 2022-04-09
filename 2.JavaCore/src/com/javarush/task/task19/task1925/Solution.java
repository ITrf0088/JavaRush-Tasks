package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        boolean bool=true;
        String[]strings = null;
        while (reader.ready()) {
            strings = reader.readLine().split(" ");
            System.out.println(Arrays.toString(strings));
            for (int i = 0; i < strings.length; i++) {
                if(strings[i].length()>6) {
                    if(!bool) writer.write(","+strings[i]);
                    else {writer.write(strings[i]);bool=false;}
                }
            }
        }
        reader.close();
        writer.close();
    }
}
