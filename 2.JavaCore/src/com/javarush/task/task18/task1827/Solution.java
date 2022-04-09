package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<Character> product = new ArrayList<>(50);

        if (args.length!=0) {
            int count = 0;
            for (int i = 1; i < args.length - 2; i++) {
                for (int j = 0; j < args[i].length(); j++) {
                    if (count == 30) break;
                    product.add(args[i].charAt(j));
                    count++;
                }
                if (count == 30) break;
                else if (i == args.length - 3) break;
                product.add(' ');
                count++;
            }
            if (product.size() < 30) {
                for (int i = product.size(); i < 30; i++) {
                    product.add(' ');
                }
            }

            int amount = 8;
            for (int i = args.length - 2; i < args.length; i++) {

                for (int j = 0; j < amount; j++) {
                    if (j < args[i].length()) {
                        product.add(args[i].charAt(j));
                    } else product.add(' ');
                }
                amount = 4;
            }

            for (int i = 0; i < 8; i++) {
                product.add(0, (char) 32);
            }

            BufferedReader bf1 = new BufferedReader(new InputStreamReader(System.in));
            String file  = bf1.readLine();
            InputStream is = new FileInputStream(file);
            OutputStream os = new FileOutputStream(file,true);
            bf1.close();
            if (is.available() < 1) {
                product.set(0, '1');
                product.set(1, '4');


                String s = "";
                for (int i = 0; i < product.size(); i++) {
                    s+=product.get(i);
                }
                os.write(s.getBytes("Cp1251"));
            }else if(is.available()>0){
                BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                int number = 0;
                while (bf.ready()){
                    int x = Integer.parseInt(bf.readLine().substring(0,8).trim());
                    if(x>number) number = x;
                }
                bf.close();

                String digits = String.valueOf(++number);
                for (int i = 0; i < digits.length(); i++) {
                    product.set(i,digits.charAt(i));
                }
                String s = "\n";
                for (int i = 0; i < product.size(); i++) {
                    s+=product.get(i);
                }

                os.write(s.getBytes("Cp1251"));
            }
            os.close();
            is.close();
        }
    }
}

