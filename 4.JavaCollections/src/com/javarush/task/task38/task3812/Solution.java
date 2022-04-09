package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {

        if(c.isAnnotationPresent(PrepareMyTest.class)){
            invoke(c,"fullyQualifiedNames");
            return true;
        }else return false;
    }

    public static boolean printValues(Class c) {

        if(c.isAnnotationPresent(PrepareMyTest.class)){
            invoke(c,"value");
            return true;
        }else return false;
    }

    public static void invoke(Class c, String name){
        try {
            Method method = PrepareMyTest.class.getDeclaredMethod(name);
            Annotation annotation = c.getAnnotation(PrepareMyTest.class);
            if(name.equals("value")){
                Class[] invoke = (Class[]) method.invoke(annotation);
                for (Class s : invoke) {
                    System.out.println(s.getSimpleName());
                }
            }else {


                String[] invoke = (String[]) method.invoke(annotation);
                for (String s : invoke) {
                    System.out.println(s);
                }
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
