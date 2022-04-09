package com.javarush.task.task34.task3401;

/* 
Числа Фибоначчи с помощью рекурсии
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        //System.out.println(solution.fibonacci(9));     //34
        System.out.println(solution.fibonacci(7));     //5
        //System.out.println(solution.fibonacci(2));     //1
       // System.out.println(solution.fibonacci(1));     //1
    }

    public int fibonacci(int n) {


        if(!(n==1 || n==2)){

            int f1 = fibonacci(n-1);
            //------------------------------------------
            int f2 = fibonacci(n-2);


            return f1+f2;

        }else {
            return 1;
        }
    }
}
