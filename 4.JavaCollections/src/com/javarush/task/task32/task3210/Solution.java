package com.javarush.task.task32.task3210;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {

        Path path = Paths.get(args[0]);
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(path.toString(),"rw");
        raf.seek(number);
        byte[] bytes = new byte[text.length()];
        raf.read(bytes,0,text.length());
        raf.seek(raf.length());

        raf.write(String.valueOf(text.equals(new String(bytes))).getBytes());



        raf.close();

    }
}
