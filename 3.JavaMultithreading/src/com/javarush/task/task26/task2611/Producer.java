package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {
    private ConcurrentHashMap<String,String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        int count=1;
        try {
            while (true) {
                map.put(String.valueOf(count),"Some text for "+count++);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.printf("[%s] thread was terminated",Thread.currentThread().getName());
        }


    }
}
