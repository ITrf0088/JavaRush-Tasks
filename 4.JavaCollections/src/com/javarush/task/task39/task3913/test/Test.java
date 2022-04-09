package com.javarush.task.task39.task3913.test;

import com.javarush.task.task39.task3913.LogParser;
import org.junit.Assert;
import org.junit.runners.JUnit4;

public class Test {

    @org.junit.Test
    public void test(){
        LogParser parser = new LogParser(null);
        String[] strings = parser.getFieldsAndValue("get  ip for  user =   \" Vasya Pupkin\"");

        Assert.assertEquals(strings.length,3);
        Assert.assertEquals(strings[0],"ip");
        Assert.assertEquals(strings[1],"user");
        Assert.assertEquals(strings[2],"Vasya");
    }
}
