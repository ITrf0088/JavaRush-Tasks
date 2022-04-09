package com.javarush.task.task18.task1825;

/* 
Собираем файл
*/
import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
                public static void main(String[] args) throws IOException {
                    List<String> list = new ArrayList<>();
                    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                    String s;
                    while (true) {
                        s = bf.readLine();
                        if(s.equals("end")) break;
                        File file = new File("C:/Users/it0088/IdeaProjects/Solution",s);
                        file.createNewFile();
                        list.add(s);
                    }
                    bf.close();
                    String fileResult = list.get(0).substring(0, list.get(0).length()-6);

                    File f = new File("C:/Users/it0088/IdeaProjects/Solution",fileResult);
                    f.createNewFile();

                    Collections.sort(list);
                    
                    OutputStream os = new FileOutputStream(f,true);
                    for (int i = 0; i < list.size(); i++) {
                        InputStream is = new FileInputStream(list.get(i));
                        byte buffer[] = new byte[is.available()];
                        is.read(buffer);
                        os.write(buffer);
                        is.close();
                    }
                    os.close();

                }
            }