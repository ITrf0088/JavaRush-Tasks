package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {

        int[][] matrix = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 1, 1,0},
                {0, 0, 0, 0},

        };

        System.out.println(maxSquare(matrix));

    }


    public static int maxSquare(int[][] matrix) {

        int maxLen = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int len = getlenOfSquare(matrix,i,j);
                if(len > maxLen){
                    if(isSquareSidesEquals(matrix,i,j,len)){
                        maxLen = len;
                    }
                }
            }
        }
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
        return maxLen * maxLen;
    }

    private static int getlenOfSquare(int[][] matrix, int pointY, int pointX) {
        int len = 0;

        for (int j = pointX; j < matrix[pointY].length; j++) {
            if (matrix[pointY][j] != 1) {
                break;
            }
            len++;
        }
        return len;
    }

    public static boolean isSquareSidesEquals(int[][] matrix, int pointY, int pointX, int lenOfSquare) {

        for (int i = pointY; i < pointY + lenOfSquare; i++) {
            if (i >= matrix.length) return false;
            for (int j = pointX; j < pointX + lenOfSquare; j++) {
                if (matrix[i][j] != 1) {
                    return false;
                }

            }
        }

        return true;
    }
}
