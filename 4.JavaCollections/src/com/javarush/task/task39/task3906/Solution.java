package com.javarush.task.task39.task3906;

/* 
Интерфейсы нас спасут!
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    public static int[] array = {9, 8, 7, 6, 0, 4, 3, 2, 1};
    public static int element = 0;

    //Arrays.sort(array);
    public static void main(String[] args) {
        int[] array1 = Arrays.copyOfRange(array,0,array.length);
        Arrays.sort(array1);
        int index = Arrays.binarySearch(array1,element);

        if(index >= 0){
            System.out.println(true);
        }

        else System.out.println(false);
        //напишите тут ваш код
    }
}
