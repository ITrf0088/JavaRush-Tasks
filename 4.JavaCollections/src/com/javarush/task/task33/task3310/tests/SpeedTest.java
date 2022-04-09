package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){

        Date idStartTime = new Date();

        for (String s : strings) {
            ids.add(shortener.getId(s));
        }

        Date idFinalTime = new Date();

        return (idFinalTime.getTime()-idStartTime.getTime());
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date idStartTime = new Date();

        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }

        Date idFinalTime = new Date();

        return (idFinalTime.getTime()-idStartTime.getTime());
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) { origStrings.add(Helper.generateRandomString());}

        Long getIdTimeMap = getTimeToGetIds(shortener1,origStrings,new HashSet<>());
        Long getIdTimeBiMap = getTimeToGetIds(shortener2,origStrings,new HashSet<>());

        Assert.assertTrue(getIdTimeMap > getIdTimeBiMap);

        Long getStrTimeMap = getTimeToGetStrings(shortener1,new HashSet<>(),origStrings);
        Long getStrTimeBiMap = getTimeToGetStrings(shortener2,new HashSet<>(),origStrings);

        Assert.assertEquals(getStrTimeMap,getStrTimeBiMap,30);

    }
}
