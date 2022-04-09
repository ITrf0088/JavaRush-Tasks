package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception {

            if(loadWheelNamesFromDB().length==0||
                    loadWheelNamesFromDB().length>Wheel.values().length||
                    loadWheelNamesFromDB().length<Wheel.values().length) throw new Exception();
            wheels = new ArrayList<>();
            for (int i = 0; i < loadWheelNamesFromDB().length; i++) {
                String s = loadWheelNamesFromDB()[i];
                try {
                    Wheel.valueOf(s);
                } catch (IllegalArgumentException e) {
                    throw new Exception();
                }

                wheels.add(Wheel.valueOf(s));
            }
                }




        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) throws Exception {
        Car car = new Car();
        for (Wheel wheel : car.wheels) {
            System.out.println(wheel);
        }

    }
}
