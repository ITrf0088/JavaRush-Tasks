package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;
    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void move(){
        for (int i = 0; i < getHorses().size(); i++) {
            getHorses().get(i).move();
        }
    }
    public void print(){
        for (int i = 0; i < getHorses().size(); i++) {
            getHorses().get(i).print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(500);
        }
    }
    public Horse getWinner(){
    int count = 0;
    Horse horse=null;
        for (int i = 0; i < horses.size(); i++) {
            if(horses.get(i).getDistance()>count){
                count = (int) horses.get(i).getDistance();
                horse = horses.get(i);
            }
        }
        return horse;
    }
    public void printWinner(){
        System.out.println("Winner is "+getWinner().getName()+"!");

    }



    public static void main(String[] args) throws InterruptedException {
        List<Horse> horse = new ArrayList<>();
        horse.add(new Horse("Bingo",3,0));
        horse.add(new Horse("Bongo",3,0));
        horse.add(new Horse("Bango",3,0));
        Hippodrome.game = new Hippodrome(horse);
        game.run();
        game.printWinner();

        
    }
}
