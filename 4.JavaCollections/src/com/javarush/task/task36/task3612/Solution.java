package com.javarush.task.task36.task3612;

import java.util.*;

/* 
Почему сет не содержит элемент?
*/

public class Solution {
    private Set<Date> dates;
    private Date lastDate;

    public static void main(String[] args) {
        Object object = new Object();
        List<Object> objects = new ArrayList<>();
        objects.add(object);
        objects.add(object);
        objects.add(object);
        System.out.println(objects.size());
        Solution solution = new Solution();
        solution.initializeDates();
        System.out.println(solution.dates.size());
        solution.updateLastDate(3_600_000L);
        System.out.println(solution.isLastDateInDates());
        System.out.println(solution.dates.size());
    }

    public boolean isLastDateInDates() {

        for (Date date : dates) {
            System.out.println(date.getTime()+": "+(date == lastDate));
        }
        return dates.contains(lastDate);
    }

    private void initializeDates() {

        dates = new HashSet<>();
        lastDate = new Date(9999999L);
        dates.add(lastDate);
        dates.add(new Date(2222222L));
        dates.add(new Date(3333333L));
        dates.add(new Date(4444444L));
        dates.add(new Date(5555555L));
    }

    protected void updateLastDate(long delta) {
        lastDate.setTime(lastDate.getTime() + delta);
        dates.add(lastDate);
    }
}