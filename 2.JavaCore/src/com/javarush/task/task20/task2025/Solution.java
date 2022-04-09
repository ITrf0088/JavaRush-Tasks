package com.javarush.task.task20.task2025;

import java.util.*;

/* 
Алгоритмы-числа
*/

public class Solution {

    public static long[] getNumbers(long N) {
        TreeSet<Long> resultSet = new TreeSet<>();

        long[][] degrees = getMatrixPow();

        int[] array = getArrWithLenAmountNumbersVarN(N);

        while (decrementArray(array)) {

            int zeroAmount = getAmountOfZero(array);
            for (int i = 0; i <= zeroAmount; i++) {

                long newNumber = 0;
                for (int j = zeroAmount; j < array.length; j++) {
                    newNumber += degrees[array[j]][array.length - i];
                }
                if (newNumber >= N || newNumber <= 0) continue;
                if (isArmstrongNumber(degrees, newNumber)) {
                    resultSet.add(newNumber);
                }
            }
        }

        long[] result = new long[resultSet.size()];
        int index = 0;
        for (Long aLong : resultSet) {
            result[index++] = aLong;
        }
        return result;
    }

    private static boolean isArmstrongNumber(long[][] degrees, long newNumber) {
        int amountOfNumber = 0;
        long divideNumber = newNumber;
        while (divideNumber != 0) {
            divideNumber = divideNumber / 10;
            amountOfNumber++;
        }

        divideNumber = newNumber;
        long degreesNumber = 0;
        while (divideNumber != 0) {
            degreesNumber += degrees[(int) (divideNumber % 1000)][amountOfNumber];
            divideNumber = divideNumber / 1000;
        }

        return degreesNumber == newNumber;

    }

    private static int getAmountOfZero(int[] array) {
        int z = 0;
        for (int j : array) {
            if (j == 0) z++;
        }
        return z;
    }

    private static boolean decrementArray(int[] array) {

        int index = 0;
        while (index < array.length && array[index] == 0) {
            index++;
        }

        if (array[array.length - 1] == 0) return false;

        Arrays.fill(array, 0, index + 1, --array[index]);
        return true;
    }

    private static int[] getArrWithLenAmountNumbersVarN(long n) {
        int[] ints = new int[String.valueOf(n).length()];
        Arrays.fill(ints, 9);
        return ints;
    }


    private static long[][] getMatrixPow() {
        long[][] longs = new long[1000][25];

        for (int i = 0; i < longs.length; i++) {
            for (int j = 0; j < longs[i].length; j++) {
                int number = i;
                long summ = 0;
                while (number != 0) {
                    long pow = 1;
                    int num = number % 10;
                    for (int m = 0; m < j; m++) {
                        pow *= num;
                    }

                    summ += pow;
                    number = number / 10;
                }

                longs[i][j] = summ;
            }
        }


        return longs;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE + 2)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024));
        System.out.println("time = " + (b - a) / 1000);


    }
}
