package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution solution = new Solution();
         solution.innerClasses = new InnerClass[]{solution.new InnerClass(),solution.new InnerClass()};
        Solution solution1 = new Solution();
        solution1.innerClasses = new InnerClass[]{solution1.new InnerClass(),solution1.new InnerClass()};


        return new Solution[]{solution,solution1};
    }

    public static void main(String[] args) {

    }
}
