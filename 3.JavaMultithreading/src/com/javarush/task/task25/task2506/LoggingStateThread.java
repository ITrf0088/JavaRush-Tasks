package com.javarush.task.task25.task2506;

import javax.swing.plaf.nimbus.State;

public class LoggingStateThread extends Thread{
    Thread target;
    public LoggingStateThread(Thread target) {
        this.target = target;
    }

    @Override
    public void run() {
        State state = null;
        State state1 =null;
        while (true) {
            state1 = target.getState();

            if(state!=state1) {
                System.out.println(state1);
            }
            if(state1==State.TERMINATED) break;
            state = state1;


        }
    }
}
