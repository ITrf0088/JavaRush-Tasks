package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(bf.readLine()));
        bf.close();
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine()).append(" ");
        }
        reader.close();

        String[] words = sb.toString().split(" ");
        System.out.println(Arrays.toString(words));
        List<Integer> index = new ArrayList<>();
        index.add(-1);

        for (int i = 0; i < words.length; i++) {
            StringBuilder strbuild = new StringBuilder(words[i]);
            String reverseStr = strbuild.reverse().toString();
            if(index.contains(i)) continue;
            for (int j = i+1; j < words.length; j++) {
                if(index.contains(j))continue;
                if(reverseStr.equals(words[j])){
                    result.add(new Pair(words[i],words[j]));
                    index.add(j);
                    break;
                }
            }
        }
        System.out.println(result.toString());


    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
