package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {

        while (true){
            InputStream is = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
            if(is.available()<1000) {
                is.close();
                throw new DownloadException();
            }
        }

    }

    public static class DownloadException extends Exception {

    }
}
