package com.javarush.task.task35.task3506;

public class Task {
    {
        System.out.println("I'am  block Task");
    }

    static {
        System.out.println("I'am static block Task");
    }

    static class Animal{{
        System.out.println("Animal");
    }}
    static class Pets {{
        System.out.println("Pets");
    }}
    static class Cat {{
        System.out.println("Cat");
    }}
    static class Dog {{
        System.out.println("Dog");
    }}

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("Task");
        System.out.println(clazz.getClassLoader());
    }
}
