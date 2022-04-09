package com.javarush.task.task36.task3601;

import java.util.HashSet;
import java.util.List;

public class Controller {
    public static void main(String[] args) {
        HashSet<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        HashSet<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);

        System.out.println(set1.removeAll(set2));
        System.out.println(set1);
        System.out.println(set2);

    }
}
