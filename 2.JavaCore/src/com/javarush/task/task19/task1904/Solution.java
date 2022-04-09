package com.javarush.task.task19.task1904;

/* 
И еще один адаптер
*/

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws IOException, ParseException {
    PersonScannerAdapter personScannerAdapter = new PersonScannerAdapter(new Scanner(System.in));
    System.out.println(personScannerAdapter.read());
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException, ParseException {
            String str = fileScanner.nextLine();
            String data[] = str.split(" ",4);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
            Date d = sdf.parse(data[3]);
            return new Person(data[1],data[2],data[0],d);


        }

        @Override
        public void close() throws IOException {
            fileScanner.close();

        }
    }
}
