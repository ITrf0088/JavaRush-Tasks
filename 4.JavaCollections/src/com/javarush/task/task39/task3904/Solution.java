package com.javarush.task.task39.task3904;

/*
Лестница
*/

public class Solution {
    private static int n = 4;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public  static long numberOfPossibleAscents(int n) {
        if (n == 1) return 1;
        long last = 0;
        long recent = 1;


        for (long i = 2; i <= n; i++) {
            long temtRecent = recent;
            recent +=  last;
            last = temtRecent;

        }

        return  recent;
    }
}

