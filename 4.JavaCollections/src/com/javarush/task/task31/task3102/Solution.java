package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> absolute = new LinkedList<>();

        File file = new File(root);
        Files.walkFileTree(Paths.get(file.toString()),new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                absolute.add(file.getParent()+"\\"+file.getFileName());

                return FileVisitResult.CONTINUE;
            }
        });
        return absolute;

    }
    public static void main(String[] args) throws IOException {
        for (String string : getFileTree("E:/folder")) {
            System.out.println(string);
        }
    }
}
