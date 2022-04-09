package com.javarush.task.task27.task2707;


/*
Определяем порядок захвата монитора
*/
public class Solution {

    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {

        synchronized (obj2) {
            synchronized (obj1) {
                System.out.println(obj1 + " " + obj2);
            }
        }

    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        Thread thread = new Thread(){
            @Override
            public void run() {
                for (int i = 0;i<10000;i++) {
                    if(isInterrupted()) break;
                    solution.someMethodWithSynchronizedBlocks(o1, o2);
                }
            }
        };

        Thread thread1 = new Thread(){
            public void synchBlock(){
                synchronized (o1){
                    synchronized (o2){
                        System.out.println("hello");
                    }
                }
            }
            @Override
            public void run() {
                for (int i = 0;i<10000;i++) {
                    if(isInterrupted()) break;
                    synchBlock();
                }
            }
        };

        thread.start();
        thread1.start();
        Thread.sleep(1000);

        return thread.getState()== Thread.State.TERMINATED;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();
        System.out.println(isLockOrderNormal(solution, o1, o2));
        System.exit(10);

    }
}
