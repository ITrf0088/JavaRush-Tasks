package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/* 
Древний Рим
*/

public class Solution {

    public static HashMap<String,Integer> data;

    static {
        data = new HashMap<>();

        data.put("I",1);data.put("V",5);data.put("X",10);
        data.put("L",50);data.put("C",100);data.put("D",500);data.put("M",1000);
    }
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        String[] chars = s.split("");

        int result = data.get(chars[chars.length-1]);

        for (int i = chars.length-1; i > 0; i--) {
            if(data.get(chars[i-1]) >= data.get(chars[i])){
                result += data.get(chars[i-1]);
            }else {
                result -= data.get(chars[i-1]);
            }
        }

        return result;
    }
}
