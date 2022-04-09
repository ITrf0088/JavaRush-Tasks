package com.javarush.task.task18.task1804;

/* 
Самые редкие байты
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fi = new FileInputStream(bf.readLine());
        List<Integer> list  = new ArrayList<>();


        while (fi.available()>0){
            list.add(fi.read());
        }
        bf.close();
        fi.close();


        int maxiteration = Collections.frequency(list,list.get(0));
        int iter = 0;
        for (int i = 0; i < list.size(); i++) {
            iter = Collections.frequency(list,list.get(i));
            if(iter<maxiteration) {
                maxiteration = iter;
            }
        }




        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            if(Collections.frequency(list,list.get(i))==maxiteration){
                set.add(list.get(i));
            }
        }

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }


    }
}



