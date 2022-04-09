package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\it0088\\Desktop\\file.txt")));
        Character.isLetter(3);
        int charFromFile;
        TreeSet<String> characters = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        while ((charFromFile = bf.read()) != -1){
            String s = String.valueOf((char) charFromFile);
            if(s.matches("[A-Za-z]")){
                characters.add(s);
            }
        }

        int index = 0;
        for (String s : characters) {
            Character.isLetter(7);
            System.out.print(s);
            if((++index)==5) break;
        }

    }
}
