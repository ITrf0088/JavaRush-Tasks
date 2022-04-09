package com.javarush.task.task18.task1820;

import java.io.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        InputStream is = new FileInputStream(bf.readLine());
        OutputStream os = new FileOutputStream(bf.readLine());
        bf.close();

        String s = "";
        while(is.available()>0){
            s += ((char)is.read());
        }
        is.close();

        String d[] = s.split("\\s+");
        for (int i = 0; i < d.length; i++) {
            String num =String.valueOf(Math.round(Float.parseFloat(d[i])));
            for (int j = 0; j < num.length() ; j++) {
                os.write(num.charAt(j));
            }
            os.write(32);
        }
        os.close();
    }
}
