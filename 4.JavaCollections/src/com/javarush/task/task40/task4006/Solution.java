package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            Socket connection = new Socket(url.getHost(),80);
            PrintWriter writer = new PrintWriter(connection.getOutputStream(),true);
            writer.println("GET "+url.getFile()+" HTTP/1.1"+"\n");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseLine;


            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}