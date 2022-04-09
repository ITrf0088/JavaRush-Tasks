package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("laeto", "laeto"));//false

    }

    public static boolean isOneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) return false;

        if(first.length() == second.length()){
            int c =0;
            for (int i = 0; i < first.length(); i++) {
                if(first.charAt(i) != second.charAt(i)) c++;
                if(c > 1) return false;
            }
            return true;
        }

        StringBuilder bigger, less;
        if (first.length() > second.length()) {
            bigger = new StringBuilder(first);
            less =  new StringBuilder(second);
        } else {
            bigger = new StringBuilder(second);
            less =  new StringBuilder(first);
        }

        for (int i = 0; i < less.length(); ) {
            if (less.charAt(i) != (bigger.charAt(i))) {
                bigger.deleteCharAt(i);
                if(!less.toString().equals(bigger.toString())){
                    return false;
                }
            } else i++;
        }

        return true;
    }
}
