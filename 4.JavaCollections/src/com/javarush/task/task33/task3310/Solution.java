package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    private Set<String> strings;

    public static void main(String[] args) {


        testStrategy(new HashMapStorageStrategy(),10000);
        System.out.println("---------------------------------------");
        testStrategy(new OurHashMapStorageStrategy(),10000);
        System.out.println("---------------------------------------");
        testStrategy(new OurHashBiMapStorageStrategy(),10000);
        System.out.println("---------------------------------------");
        testStrategy(new HashBiMapStorageStrategy(),10000);
        System.out.println("---------------------------------------");
        testStrategy(new DualHashBidiMapStorageStrategy(),10000);
        System.out.println("---------------------------------------");
        testStrategy(new FileStorageStrategy(),10);
    }


    public static Set<Long> getIds(Shortener shortener,Set<String> strings){
        Set<Long> ids = new HashSet<>();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }

        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> values = new HashSet<>();
        for (Long key : keys) {
            values.add(shortener.getString(key));
        }
        return values;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> stringsSet = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
            stringsSet.add(Helper.generateRandomString());

        }

        Shortener shortener  = new Shortener(strategy);

        Date idStartTime = new Date();
        Set<Long> ids = getIds(shortener,stringsSet);
        Date idFinalTime = new Date();
        Helper.printMessage(String.valueOf(idFinalTime.getTime()-idStartTime.getTime()));

        Date stringStartTime = new Date();
        Set<String> returnedSetStrings = getStrings(shortener, ids);
        Date stringFinalTime = new Date();
        Helper.printMessage(String.valueOf(stringFinalTime.getTime()-stringStartTime.getTime()));

        System.out.println(returnedSetStrings.size()+":"+stringsSet.size());

        if(returnedSetStrings.size() == stringsSet.size()){
            Helper.printMessage("Тест пройден.");
        }else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
