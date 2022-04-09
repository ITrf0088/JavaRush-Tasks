package com.javarush.task.task20.task2026;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    static int count2 = 0;
    public static void main(String[] args) {

        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }


    public static int getRectangleCount(byte[][] a) {
        count2=0;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == 1) {
                    list.add(j);
                    if ((j == a[i].length - 1 || a[i][j + 1] == 0)) {
                        nullpoint(list, a,i);
                        list.clear();
                    }
                }

            }



        }

        return count2;
    }

    public static void nullpoint(ArrayList<Integer> l, byte[][] a,int count) {
        boolean flag = false;
            for (int i = count; i < a.length; i++) {
                if (flag) break;
                for (int j = 0; j < l.size(); j++) {
                    if (a[i][l.get(j)] == 1) {
                        a[i][l.get(j)] = 0;
                    } else flag = true;
                }
        }
           count2++;
    }
}
