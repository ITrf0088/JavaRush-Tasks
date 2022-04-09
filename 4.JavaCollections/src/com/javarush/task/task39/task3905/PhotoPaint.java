package com.javarush.task.task39.task3905;

import java.util.Arrays;

import static com.javarush.task.task39.task3905.Color.*;

public class PhotoPaint {


    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {


        if(desiredColor == null) return false;
        boolean isInBorder = (y >= 0 && y < image.length) && (x >= 0 && x < image[y].length);
        if (!isInBorder   || (image[y][x] == desiredColor)) return false;

        Color colorForChange = Color.valueOf(image[y][x].name());
        image[y][x] = null;


        while (true) {
            boolean stopLoop = true;
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j++) {

                    if (image[i][j] == null) {
                        int left = j - 1;
                        int top = i - 1;
                        int right = j + 1;
                        int down = i + 1;

                        if (left >= 0) {
                            if (image[i][left] == colorForChange) {
                                image[i][left] = null;
                                stopLoop = false;
                            }
                        }
                        if (top >= 0) {
                            if (image[top][j] == colorForChange) {
                                image[top][j] = null;
                                stopLoop = false;
                            }
                        }

                        if (right < image[i].length) {
                            if (image[i][right] == colorForChange) {
                                image[i][right] = null;
                                stopLoop = false;
                            }
                        }

                        if (down < image.length) {
                            if (image[down][j] == colorForChange) {
                                image[down][j] = null;
                                stopLoop = false;
                            }
                        }
                    }
                }
            }
            if(stopLoop) break;

        }
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if(image[i][j] == null){
                    image[i][j] = desiredColor;
                }
            }
        }


        return true;
    }
}
