package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][] {
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'},
                {'f', 'e', 'e', 'e', 'l', 'e'},
                {'u', 's', 'n', 'n', 'n', 'o'},
                {'l', 'e', 'n', 'o', 'n', 'e'},
                {'m', 'm', 'n', 'n', 'n', 'h'},
                {'p', 'e', 'e', 'e', 'j', 'e'}
        };
        System.out.println(detectAllWords(crossword, "home", "same", "emas", "ll", "l", "rr", "sf", "one", "nn", "j"));
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }
    public static List<Word> detectAllWords(int[][] crossword, String... words) {
         List<Word> list = new ArrayList<>();
        List<String> str = new ArrayList<>();
        for (int k = 0; k < words.length; k++) {
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if(crossword[i][j]==words[k].charAt(0)){
                        if(words[k].length()>1){
                        int wlen = words[k].length()-1;
                        String  hRight = j+wlen+""+i, hleft =(j-wlen)+""+i, vDown =j+""+(i+wlen), verUp =j+""+(i-wlen),
                                dUpRight =(j+wlen)+""+(i-wlen),dDownRight = (j+wlen)+""+(i+wlen),dUpLeft = (j-wlen)+""+(i+wlen),dDownLeft = (j-wlen)+""+(i-wlen);
                        for (int w = 0; w < words[k].length(); w++) {
                            try {hRight+=(char) crossword[i][j+w];} catch (Exception e) {}
                            try { hleft+=(char) crossword[i][j-w];} catch (Exception e) {}
                            try { vDown+=(char) crossword[i+w][j];} catch (Exception e) {}
                            try {verUp+=(char) crossword[i-w][j];} catch (Exception e) {}
                            try {dUpRight+=(char) crossword[i-w][j+w];} catch (Exception e) {}
                            try {dDownRight+=(char) crossword[i+w][j+w];} catch (Exception e) {}
                            try {dUpLeft+=(char) crossword[i+w][j-w];} catch (Exception e) {}
                            try {dDownLeft+=(char) crossword[i-w][j-w];} catch (Exception e) {}
                        }
                        str.add(hRight);str.add(hleft);str.add(vDown);str.add(verUp);str.add(dUpRight);str.add(dDownRight);str.add(dUpLeft);str.add(dDownLeft);
                        for (int c = 0; c < str.size(); c++) {
                            String s = str.get(c);
                            if (s.substring(2).equals(words[k])){
                                Word word = new Word(words[k]);
                                word.setStartPoint(j,i);
                                word.setEndPoint(Integer.parseInt(s.substring(0,1)),Integer.parseInt(s.substring(1,2)));
                                list.add(word);
                            }
                        }
                            str.clear();
                    }else {
                            Word word = new Word(words[k]);
                            word.setStartPoint(j,i);
                            word.setEndPoint(j,i);
                            list.add(word);
                            str.clear();
                        }
                }
            }
        }
    }

        return list;
    }
    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
