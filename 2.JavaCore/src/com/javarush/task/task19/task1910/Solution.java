package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String filereader = bf.readLine();
        String filewriter = bf.readLine();
        bf.close();

        BufferedReader reader = new BufferedReader(new FileReader(filereader));
        BufferedWriter writer = new BufferedWriter(new FileWriter(filewriter));

        String data;
        while (reader.ready()){
            data = reader.readLine();
            writer.write(data.replaceAll("[^a-zA-Z||0-9||\\s]",""));
        }
        reader.close();
        writer.close();
    }
}

