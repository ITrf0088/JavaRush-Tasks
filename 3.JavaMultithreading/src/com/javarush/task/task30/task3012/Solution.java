package com.javarush.task.task30.task3012;
/* 
Получи заданное число
*/
import java.util.ArrayList;
import java.util.List;
public class Solution {
    public static void main(String[] args) {
        System.out.println(-1.0 / 0.0);
        Solution solution = new Solution();
        solution.createExpression(1234);
    }
    public void createExpression(int number) {
        StringBuilder sb = new StringBuilder(number+" =");
        List<Integer> list = new ArrayList<>();
        int divide = number ;
        do{
            if(divide%3 == 2){list.add(-1); divide++;}
            else if(divide%3 == 1)list.add(1);
            else if(divide%3 == 0)list.add(0);
            divide /= 3;
        }while (divide>0);
        for (int i = 0; i < list.size(); i++) {
            String token = null;
            int num = list.get(i);
            if(num==0) continue;
            if(num<0) token = " - ";
            else token = " + ";
            sb.append(token).append(Math.abs(list.get(i)* ((int) Math.pow(3, i))));
        }
        System.out.println(sb.toString());
    }
}