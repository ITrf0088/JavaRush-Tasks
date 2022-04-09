package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {


    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder where = new StringBuilder();

        for (Map.Entry<String, String> string : params.entrySet()) {
            if(string.getValue()==null) continue;
            where.append(" and ");
            where.append(String.format("%s = '%s'",string.getKey(),string.getValue()));

        }
        if(where.length()>0) return where.toString().substring(5);
        return "";
    }
}
