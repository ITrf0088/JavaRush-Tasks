package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
         BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
         String fileReader = bf.readLine();
         bf.close();
         Reader reader = new FileReader(fileReader);
        String str = "";
        while (reader.ready()){
            str +=(char) reader.read();
        }
        reader.close();

        String[] string = str.split("\\W");
        System.out.println(Arrays.toString(string));
        System.out.println(Collections.frequency(Arrays.asList(string),"world"));

    }
}


//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        String fileReader = bf.readLine();
//        bf.close();
//        Reader reader = new FileReader(fileReader);
//
//        String str = "";
//        while (reader.ready()){
//            str += reader.read();
//        }
//
//
//        String[] string = str.split("[^\\da-zA-Z]");
//        System.out.println(Collections.frequency(Arrays.asList(string),"world"));
//
//    }
//}
