package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable,CustomThreadManipulator {
    Thread thread;

    @Override
    public void run() {
        System.out.println(this.thread.getName());
        while (!thread.isInterrupted()) {
            try {
                Thread.sleep(100);
                System.out.println(this.thread.getName());
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void start(String threadName) {
        this.thread =  new Thread(this,threadName);
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();

    }
}
