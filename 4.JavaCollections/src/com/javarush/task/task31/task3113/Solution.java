package com.javarush.task.task31.task3113;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        System.out.print("Вводите путь папки:");
        Path pathToFile = Paths.get(new BufferedReader(new InputStreamReader(System.in)).readLine());

        if(Files.isDirectory(pathToFile)){
            System.out.println(String.format("Всего папок - %d",totalFolders(pathToFile)));
            System.out.println(String.format("Всего файлов  - %d",totalFiles(pathToFile)));
            System.out.println(String.format("Общий размер - %d",totalSizeOfFolder(pathToFile)));
        }else if (!Files.isDirectory(pathToFile)){
            System.out.println(String.format("%s - не папка",pathToFile));
        }
    }

    static public int totalFolders(Path file) throws IOException {
        AtomicInteger countfolders = new AtomicInteger(0);
        Files.walkFileTree(file,new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                countfolders.incrementAndGet();
                return FileVisitResult.CONTINUE;
            }
        });
        return countfolders.get()-1;
    }
    static public int totalFiles(Path file) throws IOException {
        AtomicInteger countFiles = new AtomicInteger(0);
        Files.walkFileTree(file,new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("dsadsadas");
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                countFiles.incrementAndGet();
                return FileVisitResult.CONTINUE;
            }
        });

        return countFiles.get();
    }
   static public long totalSizeOfFolder(Path file) throws IOException {
       AtomicLong countFiles = new AtomicLong(0);
       Files.walkFileTree(file,new SimpleFileVisitor<Path>(){
           @Override
           public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
               countFiles.set(countFiles.get()+Files.size(file));
               return FileVisitResult.CONTINUE;
           }
       });

       return countFiles.get();
   }

}
