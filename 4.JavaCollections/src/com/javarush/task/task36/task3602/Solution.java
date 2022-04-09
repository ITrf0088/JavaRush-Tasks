package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<Collections> collectionsClass = Collections.class;
        for (Class<?> aClass : collectionsClass.getDeclaredClasses()) {
            if(List.class.isAssignableFrom(aClass)){
                int mode = aClass.getModifiers();
                if(Modifier.isPrivate(mode)&&Modifier.isStatic(mode)){
                    try {
                        Method method = aClass.getDeclaredMethod("get",int.class);
                        method.setAccessible(true);

                        Constructor<?> constructor = aClass.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        Object o = constructor.newInstance();

                        method.invoke(o,1);

                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        if(e.getCause() instanceof IndexOutOfBoundsException){
                            return aClass;
                        }
                    }
                    }
                }
            }
        return null;
        }

    }

