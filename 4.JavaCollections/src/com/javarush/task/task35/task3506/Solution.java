package com.javarush.task.task35.task3506;

import java.sql.SQLException;

/* 
Wildcards
*/

public class Solution {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class<?> clazz = Class.forName("Task");
        System.out.println(clazz.getClassLoader());



    }

}
