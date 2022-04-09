package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        BufferedReader bf1 = new BufferedReader(new InputStreamReader(is));


        while (bf1.ready()){
            String s = bf1.readLine();
            for (int j = 0; j < args.length; j++) {
                if (s.startsWith(args[0]+" ")){
                    System.out.println(s);
                    break;
                }
            }
        }
        is.close();
        bf1.close();

    }
}



