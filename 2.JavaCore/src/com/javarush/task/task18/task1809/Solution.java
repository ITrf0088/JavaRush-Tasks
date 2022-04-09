package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        InputStream is1 = new FileInputStream(input());
        OutputStream is2 = new FileOutputStream(input());


        byte [] buffer = new byte[is1.available()];
        is1.read(buffer);
        System.out.println(buffer.length);
        for (int i = buffer.length-1; i>=0; i--) {
            is2.write(buffer[i]);
        }


        is1.close();
        is2.close();
    }



    public static String input() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}


