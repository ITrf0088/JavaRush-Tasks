package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String file = bf.readLine();
        bf.close();

        StringBuilder string = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            string.append(reader.readLine());
        }
        reader.close();
        List<String> list = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        Pattern pattern = Pattern.compile(args[0]);
        Matcher matcher = pattern.matcher(string.toString());

        while (matcher.find()) {
            l1.add(matcher.start());
            l2.add(matcher.end());
        }
        for (int j = 0; j < l1.size(); j++) {
            int count = 0;
            if(string.charAt(l1.get(j) - 1)!='<'&&string.charAt(l1.get(j) - 1)!='/') continue;
            for (int i = j; i < l1.size(); i++) {
                int index = l1.get(i) - 1;
                if (string.charAt(index) == '<') {
                    count++;
                } else if (string.charAt(index) == '/') {
                    count--;
                    if (count == 0) {
                        list.add(string.substring(l1.get(j) - 1, l2.get(i) + 1));
                        break;
                    }
                    else if(count<0)break;
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}

