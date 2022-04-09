package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(args[0]));
        BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));




        String string [] = null ;
        String str = "";
        while (bf.ready()){
            string = bf.readLine().split(" ");
            str = "";
            for (int i = 0; i < string.length; i++) {
                for (int i1 = 0; i1 < string[i].length(); i1++) {
                    if(Character.isDigit(string[i].charAt(i1))){
                        str+=string[i]+" ";
                        break;
                    }
                 }
            }
            bw.write(str);
        }
        bf.close();
        bw.close();



    }
}
