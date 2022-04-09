package com.javarush.task.task36.task3609;

/* 
Рефакторинг MVC
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<Integer> aList = new ArrayList<>();
        List<Integer> lList = new LinkedList<>();

        measureTime(aList);
        measureTime(lList);
    }

    public static void measureTime(List<Integer> list){

        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(i,1);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(list.getClass().getSimpleName()+": "+(t2-t1));

    }

}