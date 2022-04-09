package com.javarush.task.task37.task3706;

import java.util.*;
import java.util.function.ToIntFunction;

/* 
Давно забытый Array
*/

public class Solution {


    public static void main(String[] args) {
        TreeMap<Integer,String> treeMap = new TreeMap<>(Comparator.comparingInt(new ToIntFunction<Integer>() {
            @Override
            public int applyAsInt(Integer value) {
                return 0;
            }
        }));

    }

}
