package com.javarush.task.task39.task3905;

import java.util.Arrays;

import static com.javarush.task.task39.task3905.Color.*;

/* 
Залей меня полностью
*/

public class Solution {
    public static void main(String[] args) {

        Color[][] matrix = new Color[][]{
                {RED, RED, RED, BLUE, BLUE, YELLOW, BLUE, BLUE, BLUE, RED},
                {RED, RED, BLUE, BLUE, BLUE, YELLOW, BLUE, RED, BLUE, RED},
                {RED, BLUE, BLUE, BLUE, BLUE, YELLOW, BLUE, RED, BLUE, RED},
                {RED, RED, RED, BLUE, BLUE, BLUE, BLUE, RED, RED, RED},
                {RED, RED, BLUE, BLUE, YELLOW, RED, BLUE, BLUE, BLUE, RED},
                {BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE},
                {RED, YELLOW, RED, RED, BLUE, BLUE, RED, BLUE, RED, RED},
                {RED, YELLOW, BLUE, RED, RED, BLUE, RED, RED, RED, RED},
                {RED, YELLOW, BLUE, RED, RED, BLUE, RED, RED, RED, RED},
                {RED, YELLOW, BLUE, RED, RED, BLUE, BLUE, BLUE, BLUE, BLUE}};

        PhotoPaint photoPaint = new PhotoPaint();
        System.out.println(photoPaint.paintFill(matrix, 9, 9, INDIGO));

        for (Color[] colors : matrix) {
            System.out.println(Arrays.toString(colors));
        }


        System.out.println("-------------------------------------------------------------------");

        Color[][] matri = new Color[][]{
                {RED, RED, RED, BLUE, BLUE, YELLOW, BLUE, BLUE, BLUE, RED},
                {RED, RED, BLUE, BLUE, BLUE, YELLOW, BLUE, RED, BLUE, RED},
                {RED, BLUE, BLUE, BLUE, BLUE, YELLOW, BLUE, RED, BLUE, RED},
                {RED, RED, RED, BLUE, BLUE, BLUE, BLUE, RED, RED, RED},
                {RED, RED, BLUE, BLUE, YELLOW, RED, BLUE, BLUE, BLUE, RED},
                {BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE},
                {RED, YELLOW, RED, RED, BLUE, BLUE, RED, BLUE, RED, RED},
                {RED, YELLOW, BLUE, RED, RED, BLUE, RED, RED, RED, RED},
                {RED, YELLOW, BLUE, RED, RED, BLUE, RED, RED, RED, RED},
                {RED, YELLOW, BLUE, RED, RED, BLUE, BLUE, BLUE, BLUE, BLUE}};

        System.out.println(paintFill(matri, 9, 9, INDIGO));

        for (Color[] colors : matri) {
            System.out.println(Arrays.toString(colors));
        }


    }

    public static boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (y < 0 || y >= image.length || x < 0 || x >= image[0].length) {
            return false;
        }

        Color originalColor = image[y][x];
        if (originalColor == desiredColor) {
            return false;
        }

        paintFill(image, x, y, desiredColor, originalColor);
        return true;
    }

    private static void paintFill(Color[][] image, int x, int y, Color desiredColor, Color originalColor) {
        if (y < 0 || y >= image.length || x < 0 || x >= image[0].length) {
            return;
        }

        if (image[y][x] == originalColor) {
            image[y][x] = desiredColor;
            paintFill(image, x - 1, y, desiredColor, originalColor);
            paintFill(image, x + 1, y, desiredColor, originalColor);
            paintFill(image, x, y - 1, desiredColor, originalColor);
            paintFill(image, x, y + 1, desiredColor, originalColor);

        }
    }
}
