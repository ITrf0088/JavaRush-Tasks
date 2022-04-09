package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String fileReader = bf.readLine();
        String fileWriter = bf.readLine();
        bf.close();

        Reader reader = new FileReader(fileReader);
        Writer writer = new FileWriter(fileWriter);

        while (reader.ready()){
            reader.read();
            writer.write(reader.read());
        }
        reader.close();
        writer.close();
    }
}
