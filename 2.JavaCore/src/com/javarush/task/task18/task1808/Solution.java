package com.javarush.task.task18.task1808;

import java.io.*;
/* 
Разделение файла
*/

public class Solution {
 public static void main(String[] args) throws IOException {

        InputStream is1 = new FileInputStream(input());
        OutputStream is2 = new FileOutputStream(input());
        OutputStream is3 = new FileOutputStream(input());

        byte [] buffer = new byte[is1.available()];
        int count = is1.read(buffer);
     if(buffer.length % 2 !=0) {
         is2.write(buffer, 0, (count / 2) + 1);
         is3.write(buffer,(count / 2) + 1 , (count/2));
     }else{
         is2.write(buffer, 0, (count / 2) );
         is3.write(buffer,(count / 2) , count / 2);
     }


        is1.close();
        is3.close();
        is2.close();
    }

    

    public static String input() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}

