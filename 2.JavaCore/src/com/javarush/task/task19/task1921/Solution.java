package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader bf = new BufferedReader(new FileReader(args[0]));

        SimpleDateFormat format = new SimpleDateFormat("dd M yyyy");
        while (bf.ready()) {
            String  string = bf.readLine();
            String []s  =string.split(" \\d",2);
            String []s1  =string.split("\\W ");
            PEOPLE.add(new Person(s[0],format.parse(s1[s1.length-1])));
        }
        bf.close();


    }
}
