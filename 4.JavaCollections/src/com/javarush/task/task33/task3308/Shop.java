package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@XmlRootElement
@XmlType(name = "shop")
public class Shop {

    public Goods goods;

    public int count;

    public double profit;

    public String[] secretData;


    static class Goods {

    public List<String> names;
    }

    public Shop(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return Arrays.toString(secretData)+":"+profit+":"+count;
    }
}
