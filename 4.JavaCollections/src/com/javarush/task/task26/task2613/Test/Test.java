package com.javarush.task.task26.task2613.Test;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.PrintWriter;
import java.io.Writer;

public class Test {

    @org.junit.Test
    public void test(){

        String s = null;
        try {
            s = ConsoleHelper.askCurrencyCode();
        } catch (InterruptOperationException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
