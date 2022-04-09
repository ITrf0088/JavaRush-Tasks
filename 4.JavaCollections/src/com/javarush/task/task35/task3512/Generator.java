package com.javarush.task.task35.task3512;

public class Generator<T> {

    Class clss;

    public Generator(Class c) {
        this.clss = c;
    }

    T newInstance() {
        try {
            return (T) clss.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}
