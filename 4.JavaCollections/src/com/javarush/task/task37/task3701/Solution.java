package com.javarush.task.task37.task3701;

import java.lang.reflect.Field;
import java.util.*;

/* 
Круговой итератор
*/

public class Solution<T> extends ArrayList<T> {



    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        Iterator<Integer> iter = list.iterator();
        while (true){

            //1 2 3 1 2 3 1 2 3 1
            System.out.print( iter.next()+ " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }


}
