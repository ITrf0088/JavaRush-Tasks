package com.javarush.task.task19.task1909;

/* 
Замена знаков
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
            writer.write(data.replace(".","!"));
        }
        reader.close();
        writer.close();
    }
}
