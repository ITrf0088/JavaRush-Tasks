package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write("test string".getBytes());
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        System.out.println(Arrays.toString("test string".getBytes()));
        System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));
        System.out.println(new String(byteArrayOutputStream.toByteArray()));


        return false;
    }
}
