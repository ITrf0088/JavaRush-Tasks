package com.javarush.task.task22.task2203;

import java.util.Arrays;

/*
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if(string==null) throw new TooShortStringException();
        string+=" ";
        String s[] = string.split("\t");
        if(s.length <3) throw new TooShortStringException();
        return s[1];
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис обучения Java\t."));
    }
}
