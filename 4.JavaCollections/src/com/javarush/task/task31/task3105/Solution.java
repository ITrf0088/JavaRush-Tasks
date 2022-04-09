package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];


        ZipInputStream zis = new ZipInputStream(new FileInputStream(args[1]));
        ZipEntry zipEntryZis;
        Map<String,ByteArrayOutputStream> zipData = new HashMap<>();

        while ((zipEntryZis = zis.getNextEntry()) != null) {
            ByteArrayOutputStream bytesArr = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int length;
            while ((length = zis.read(bytes)) >= 0) {
                bytesArr.write(bytes, 0, length);
            }
            zipData.put(zipEntryZis.getName(),bytesArr);
            zis.closeEntry();
        }
        zis.close();



        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(args[1]));
        if(!zipData.containsKey("new"+"/"+Paths.get(fileName).getFileName().toString())){
            zos.putNextEntry(new ZipEntry("new"+File.separator+Paths.get(fileName).getFileName().toString()));
            Files.copy(Paths.get(fileName),zos);
            zos.closeEntry();
        }

        for (Map.Entry<String, ByteArrayOutputStream> entry : zipData.entrySet()) {
            zos.putNextEntry(new ZipEntry(entry.getKey()));
            if(entry.getKey().equals("new"+"/"+Paths.get(fileName).getFileName().toString())){
                Files.copy(Paths.get(fileName),zos);
            }else {
                zos.write(entry.getValue().toByteArray());
            }
            zos.closeEntry();
        }
        zos.close();
    }
}
