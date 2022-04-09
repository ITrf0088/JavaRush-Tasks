package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String filereader = bf.readLine();
        String filewriter = bf.readLine();
        bf.close();

       BufferedReader reader = new BufferedReader(new FileReader(filereader));
        BufferedWriter writer = new BufferedWriter(new FileWriter(filewriter));

        String string = "";
        while (reader.ready()){
            string += ((char) reader.read());
        }
        reader.close();

        String [] str = string.split(" ");

        for (int i = 0; i < str.length; i++) {
            if(str[i]!=" ") {
                if(str[i].matches("\\d+"))
                writer.write(str[i]+" ");
            }
        }
        writer.close();
        }
    }

