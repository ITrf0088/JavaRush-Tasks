package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("gararag"));
        System.out.println(isPalindromePermutation("annnffff"));

    }

    public static boolean isPalindromePermutation(String s) {
        s = s.toLowerCase();
        Set<Character> characters = s.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());

        int c = 0;
        for (Character ch : characters) {
            String sameChars = s.replaceAll("[^"+ch+"]","");
            if(sameChars.length() % 2 == 1){
                c++;
            }
            if(c>1) return false;
        }

        return true;
    }
}
