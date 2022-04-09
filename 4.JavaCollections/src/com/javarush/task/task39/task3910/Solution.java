package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/

public class Solution {
    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        System.out.println(isPowerOfThree(50000000));
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);
        long l3 = System.currentTimeMillis();
        System.out.println(isPowerOfThree1(50000000));
        long l4 = System.currentTimeMillis();
        System.out.println(l4-l3);


    }

    private static boolean isPowerOfThree1(int n) {
        for (int i = 1; i <= n; i*=3) {
            if (i == n) return true;

        } return false;
    }

    public static boolean isPowerOfThree(int n) {
        for (int i = 0; i < n; i++) {
            double pow = Math.pow(3, i);
            if(pow == n){
                return true;
            }

            if(pow > n ) return false;
        }
        return false;
    }
}
