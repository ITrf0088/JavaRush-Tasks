package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        try (FileOutputStream os = new FileOutputStream(args[1]);
             FileInputStream is = new FileInputStream(args[0])) {
            byte[] isArr = new byte[is.available()];
            is.read(isArr);
            String isStr = new String(isArr, "Windows-1251");
            os.write(isStr.getBytes(StandardCharsets.UTF_8));
        }
    }
}
