package com.javarush.task.task22.task2202;

import java.util.Arrays;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("Амиго и Диего  лучшие"));
    }


    public static String getPartOfString(String string) {
        if(string==null) throw new TooShortStringException();
        String s[] = string.split(" ");
        if(s.length < 5) throw new TooShortStringException();
        return s[1]+" "+ s[2]+" "+s[3]+" "+s[4];
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
