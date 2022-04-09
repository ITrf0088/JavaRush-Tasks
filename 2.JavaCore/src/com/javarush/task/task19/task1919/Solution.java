package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(args[0]));
        Map<String,Double> map = new HashMap<>();


        String string="";
        String [] data=null;
        while (bf.ready()){
            string=bf.readLine();
            data = string.split(" ");
            if(map.containsKey(data[0])){
            map.put(data[0],Double.parseDouble(data[1])+Double.parseDouble(String.valueOf(map.get(data[0]))));
            }else
                map.put(data[0],Double.parseDouble(data[1]));
        }
        bf.close();

        TreeMap<String,Double> treeMap = new TreeMap<>(map);
        for (Map.Entry<String,Double> m : treeMap.entrySet()) {
            System.out.println(m.getKey()+" "+m.getValue());

        }
    }
}
