package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader_1 = new BufferedReader(new FileReader(bf.readLine()));
        BufferedReader reader_2 = new BufferedReader(new FileReader(bf.readLine()));
        bf.close();

        ArrayList<String> f1 = new ArrayList<>();
        ArrayList<String> f2 = new ArrayList<>();

        while (reader_1.ready()) {
            f1.add(reader_1.readLine());
        }
        reader_1.close();

        while (reader_2.ready()) {
            f2.add(reader_2.readLine());
        }
        reader_2.close();

        boolean flag = false;

        while (!flag) {
            try {
                if (f1.get(0).equals(f2.get(0))) {
                    lines.add(new LineItem(Type.SAME, f1.remove(0)));
                    f2.remove(0);
                } else if (!f1.get(0).equals(f2.get(0))) {
                    if (f1.get(0).equals(f2.get(1))) {
                        lines.add(new LineItem(Type.ADDED, f2.remove(0)));
                        lines.add(new LineItem(Type.SAME, f1.remove(0)));
                        f2.remove(0);
                    } else if (!f1.get(0).equals(f2.get(1))) {
                        lines.add(new LineItem(Type.REMOVED, f1.remove(0)));
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                if (f1.size() != 0) {
                    for (int i = 0; i < f1.size(); i++) {
                        lines.add(new LineItem(Type.REMOVED, f1.remove(0)));
                    }
                    break;
                } else if (f2.size() != 0) {
                    for (int i = 0; i < f2.size(); i++) {
                        lines.add(new LineItem(Type.ADDED, f2.remove(0)));
                    }
                } else {
                    lines.add(new LineItem(Type.REMOVED, f1.remove(0)));
                    lines.add(new LineItem(Type.ADDED, f2.remove(0)));
                    break;
                }
            }

        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
