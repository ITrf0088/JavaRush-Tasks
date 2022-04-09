package com.javarush.task.task40.task4009;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getWeekdayOfBirthday("30.05.1984", "2015"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year) {
        LocalDate date = LocalDate.parse(birthday,DateTimeFormatter.ofPattern("d.M.y").withLocale(Locale.ITALIAN));
        DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("y");
        Year yearDate = Year.parse(year, yearFormat);
        return date.withYear(yearDate.getValue()).format(DateTimeFormatter.ofPattern("EEEE").withLocale(Locale.ITALIAN));

    }
}
