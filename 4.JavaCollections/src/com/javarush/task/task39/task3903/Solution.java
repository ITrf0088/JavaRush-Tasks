package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/* 
Неравноценный обмен
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        System.out.println(swapBits1(65431, 14, 8));
        main();
    }
    public static void main() throws IOException {
        System.out.println(swapBits(65431, 3, 0));

    }

    public static long swapBits1(long number, int i, int j) {
        System.out.println(Long.toBinaryString(number));
            long bitMask = (1L << i) | (1L << j);
            System.out.println(Long.toBinaryString(bitMask));
            number ^= bitMask;
        System.out.println(Long.toBinaryString(number));
        return number;
    }

    public static long swap(long number, int pos, long data) {
        if(data == 1){
            return number | (1L << pos);
        } else{
            return number & ~(1L << pos);
        }
    }





    public static long swapBits(long number, int i, int j) {
        long iIndex = (1 & (number >>> (i)));
        long jIndex = (1 & (number >>> (j)));

        number = swap(number, i, jIndex);
        number = swap(number, j, iIndex);

        return number;
    }

}

