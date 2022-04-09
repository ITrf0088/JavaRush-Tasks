package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
*/
 class A extends Solution {

    @Override
    public String toString() {
        return "A";
    }
}

 class B extends A {
    @Override
    public String toString() {
        return "B";
    }
}
 class C extends A {
    @Override
    public String toString() {
        return "C";
    }
}

public class Solution<T extends A> {



    static <T extends A> void give(List<? extends A> list , T t){

    }

    public static void main(String[] args) {
        List<B> list = new ArrayList<>(Arrays.asList(new B(),new B(),new B()));
        give(list,new B());


    }
}