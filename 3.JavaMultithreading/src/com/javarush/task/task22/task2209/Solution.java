package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(bf.readLine()));
        bf.close();
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine()).append(" ");
        }
        reader.close();
        StringBuilder result = getLine(sb.toString().split(" "));
        System.out.println(result.toString());

    }

    public static StringBuilder getLine(String... words) {
        List<String> list = new ArrayList<>(Arrays.asList(words));
        int flag=0;

            for (int k =0;k<list.size();k++) {
                if(flag==list.size()-1) break;
                list.add(0,list.remove(list.size()-1));
                for (int i = 0; i < list.size(); i++) {
                    char end = Character.toLowerCase(list.get(i).charAt(list.get(i).length()-1));
                    for (int j = i+1; j < list.size(); j++) {
                        char start = Character.toLowerCase(list.get(j).charAt(0));
                        if(end==start){
                            String str = list.remove(j);
                            list.add(i + 1, str);
                            break;
                        }
                    }
                }
                try {
                    for (; flag <list.size() ; flag++) {
                        if(Character.toLowerCase(list.get(flag).charAt(list.get(flag).length()-1))
                                !=Character.toLowerCase(list.get(flag+1).charAt(0))){
                            break;
                        }
                    }
                } catch (Exception e) {

                }
            }



        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(" ");
        }
        return sb;
    }
}
