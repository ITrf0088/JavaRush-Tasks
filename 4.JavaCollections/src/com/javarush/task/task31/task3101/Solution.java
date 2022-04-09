package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Проход по дереву файлов
*/
public class Solution {

    public static void main(String[] args) throws Exception {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);

        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        if (!FileUtils.isExist(allFilesContent))
            FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
        else{
            FileUtils.deleteFile(allFilesContent);
            FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
        }

        FileOutputStream outputStreamRename = new FileOutputStream(allFilesContent);
        ArrayList<File> files = new ArrayList<>();
        processing(path,files);
        Collections.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStreamRename)))
        {
            for (File file : files) {
                if(file.length()<=50)
                try(BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
                    while (bf.ready()) {
                        writer.write(bf.readLine());
                    }
                    writer.write("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            outputStreamRename.close();
        }

    }

    private static void processing(File path,ArrayList<File> files) {
        for (File file : path.listFiles()) {
            System.out.println(file);
            if(file.isFile()){
                if(file.length()<=50){
                    files.add(file);
                }
            }
            else if(file.isDirectory()){
                processing(file,files);
            }

        }

    }
    }

