package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        Path path = Paths.get(args[0]);
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(path.toString(),"rw");

        if(number > raf.length())
            raf.seek(raf.length());
        else raf.seek(number);

        raf.write(text.getBytes());
        raf.close();

    }
}
