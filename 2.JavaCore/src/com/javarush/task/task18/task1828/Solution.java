package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            BufferedReader bff = new BufferedReader(new InputStreamReader(System.in));
            String s = bff.readLine();
            bff.close();
            List<String> list = new ArrayList<>();
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(s)));
            while (bf.ready()) {
                list.add(bf.readLine());
            }
            bf.close();

            if (args[0].equals("-u")) {
                String productName = "";
                for (int i = 2; i < args.length - 2; i++) {
                    productName += args[i];
                    productName += " ";
                }
                String fulldata = String.format("%-8.8s%-30.30s%-8.8s%-4.4s", args[1], productName, args[args.length - 2], args[args.length - 1]);
                int i;
                for (i = 0; i < list.size(); i++) {
                    int digit = Integer.parseInt(list.get(i).substring(0, 8).trim());
                    if (digit == Integer.parseInt(args[1])) {
                        list.set(i, fulldata);
                        break;
                    }
                }
            } else if (args[0].equals("-d")) {
                for (int i = 0; i < list.size(); i++) {
                    int digit = Integer.parseInt(list.get(i).substring(0, 8).trim());
                    if (digit == Integer.parseInt(args[1])) {
                        list.remove(i);
                        break;
                    }
                }
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(s)));
            bw.write(list.get(0));
            for (int i1 = 1; i1 < list.size(); i1++) {
                bw.write("\n" + list.get(i1));
            }
            bw.close();
        }
    }
}
