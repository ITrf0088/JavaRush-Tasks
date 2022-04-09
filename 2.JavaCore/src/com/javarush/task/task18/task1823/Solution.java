package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.*;

/* 
Нити и байты
*/

public class Solution {
    public volatile static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s ;
        while (true){
            s = bf.readLine();
            if (s.equals("exit")) break;
            new ReadThread(s).start();
        }
        bf.close();


    }

    public static class ReadThread extends Thread {
        InputStream is = new FileInputStream(getName());
        byte buffer [] = new byte[is.available()];
        List<Byte> list = new ArrayList<>();
        Set<Byte> set = new TreeSet<>();

        public ReadThread(String fileName) throws IOException {
            super(fileName);
        }
        @Override
        public void run() {
            try {
                operation();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private synchronized void operation() throws IOException {
            is.read(buffer);
            for (byte b : buffer) {
                list.add(b);
                set.add(b);
            }

            int count = 0;
            byte b = 0;
            for (Byte aByte : set) {
                int max = Collections.frequency(list,aByte);
                if(max>count) {
                    count = max;
                    b = aByte;
                }
                resultMap.put(getName(),(int)b);
            }

        }


    }
}
