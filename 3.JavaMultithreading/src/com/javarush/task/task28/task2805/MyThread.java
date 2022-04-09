package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static AtomicInteger num = new AtomicInteger(0);
    public MyThread() {
        this.setPriority(num.incrementAndGet());
        if(num.get()==10) {
            num.set(0);
        }

    }

    public MyThread(Runnable target) {
        super(target);
        this.setPriority(num.incrementAndGet());
        if(num.get()==10) {
            num.set(0);
        }
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        this.setPriority(num.incrementAndGet());
        if(num.get()==10) {
            num.set(0);
        }
    }

    public MyThread(String name) {
        super(name);
        this.setPriority(num.incrementAndGet());
        if(num.get()==10) {
            num.set(0);
        }
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        this.setPriority(num.incrementAndGet());
        if(num.get()==Thread.currentThread().getThreadGroup().getMaxPriority()) {
            num.set(0);
        }
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        this.setPriority(num.incrementAndGet());
        if(num.get()==10) {
            num.set(0);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        this.setPriority(num.incrementAndGet());
        if(num.get()==10) {
            num.set(0);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        this.setPriority(num.incrementAndGet());
        if(num.get()==10) {
            num.set(0);
        }
    }


}
