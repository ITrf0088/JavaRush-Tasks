package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(sort(new Integer[]{5 ,8, 13, 15, 17})));
    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);

        double mediana;
        if(array.length%2 != 0) mediana = array[(array.length/2)];
        else mediana = (array[(array.length/2)] + array[((array.length-1)/2)]) /2.0;

        Arrays.sort(array, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(Arrays.toString(array));
                return (int) (Math.abs(mediana-o1) - Math.abs(mediana-o2));

            }
        });
        System.out.println();
        return array;
    }
}
