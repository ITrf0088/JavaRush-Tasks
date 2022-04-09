package com.javarush.task.task30.task3006;

import com.javarush.task.task21.task2109.Solution;

import java.math.BigInteger;

public class Pair {
    private int x;
    private int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("x=%d, y=%d", x, y);
    }

    public void swap() {
        x = x^y;
        y = y^x;
        x = x^y;
    }

}
