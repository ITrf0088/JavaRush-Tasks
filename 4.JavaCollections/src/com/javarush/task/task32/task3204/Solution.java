package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());

    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        Random random = new Random();
        StringBuilder password = new StringBuilder("");

        boolean hasAllChar = false;
        boolean isnumber = false, isUpper = false, isDown = false;

        do {
                int count = random.nextInt(3)+1;
                switch (count){
                    case 1: password.append((char) (random.nextInt(10)+'0'));isnumber=true; break;
                    case 2: password.append((char) (random.nextInt(26)+'A'));isUpper=true; break;
                    case 3: password.append((char) (random.nextInt(26)+'a'));isDown=true; break;
                }


        } while (password.toString().length() != 8 || (!isDown && !isnumber  && !isUpper));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(password.toString().getBytes());
        return byteArrayOutputStream ;

    }
}