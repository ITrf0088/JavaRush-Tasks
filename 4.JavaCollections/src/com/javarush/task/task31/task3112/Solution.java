package com.javarush.task.task31.task3112;

import sun.security.krb5.internal.PAData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path passwords = downloadFile("https://i.pinimg.com/originals/bd/e4/c5/bde4c5925a4e62a99e9158c28cb058b0.jpg", Paths.get("E:/folder"));

        System.out.println(Files.readAllBytes(passwords));
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException, URISyntaxException {
        URL url = new URL(urlString);
        InputStream stream = url.openStream();

        Path tempFile = Files.createTempFile("temp",".tm");
        Files.copy(stream,tempFile,StandardCopyOption.REPLACE_EXISTING);

        Path path =Paths.get(downloadDirectory.toString() + "/"+Paths.get(url.getFile()).getFileName());
        if(!Files.exists(path)) path =Files.createFile(Paths.get(downloadDirectory.toString() + "/"+Paths.get(url.getFile()).getFileName()));

        return Files.move(tempFile,path,StandardCopyOption.REPLACE_EXISTING);

    }
}
