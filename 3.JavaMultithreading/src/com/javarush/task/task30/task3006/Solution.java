package com.javarush.task.task30.task3006;

import java.math.BigInteger;

/*
Swap по-новому
*/
public class Solution {
    public static void main(String[] args) {
        /* expected output
        x=4, y=5
        x=5, y=4
         */
        BigInteger bigInteger = new BigInteger("001",2);
        BigInteger bigInteger1 = new BigInteger("100",2);
        BigInteger bigInteger2 = new BigInteger("101",2);

        System.out.println(bigInteger);
        System.out.println(bigInteger1);
        System.out.println(bigInteger2);

        Pair pair = new Pair(4, 5);
        System.out.println(pair);
        pair.swap();
        System.out.println(pair);
    }
}
