package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public int methodThrowsClassCastException() {
        try
        {
            return 1;
        }
        finally
        {
            return 0;
        }

    }

    public void methodThrowsNullPointerException() {
        String s = null;
        System.out.println(s.trim());

    }

    public static void main(String[] args) {
        System.out.println(new VeryComplexClass().methodThrowsClassCastException());
    }
}
