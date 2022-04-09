package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static int division(int n1, int n2){
        int count = 0, maxdiv = 1;

        if(n1<n2) count = n1;
        else count = n2;

        for (int i = 1; i <=count ; i++) {
            if((n1 % i == 0) && (n2 % i == 0)){
                if(i>maxdiv) maxdiv = i;
            }
        }
        return maxdiv;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n1 = Integer.parseInt(bf.readLine());
        int n2 = Integer.parseInt(bf.readLine());

        if(n1<=0 || n2<=0 ) throw new Exception();
        else System.out.println(division(n1, n2));

    }
}

