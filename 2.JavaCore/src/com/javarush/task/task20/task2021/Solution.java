package com.javarush.task.task20.task2021;

import java.io.*;

/*
Сериализация под запретом
*/
public class Solution implements Serializable {
    public static class SubSolution extends Solution  {
        private void writeObject( ObjectOutputStream o) throws IOException {
            throw new NotSerializableException("ds");
        }

        private void readObject(ObjectInputStream  o) throws IOException {
            throw new NotSerializableException("Sds");
        }


        }


    public static void main(String[] args) {

    }
}
