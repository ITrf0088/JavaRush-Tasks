package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;

    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        ArrayList<Boolean> booleans = new ArrayList<>();

        if(this.partOfName!=null) {
            boolean partOfName = true;
            if (!file.getFileName().toString().contains(this.partOfName))
                partOfName = false;
            booleans.add(partOfName);
        }

        if(this.partOfContent!=null) {
            String string = new String(Files.readAllBytes(file));
            boolean partOfContent = true;
            if (!string.contains(this.partOfContent))
                partOfContent = false;
            booleans.add(partOfContent);
        }

        if(this.maxSize!=0) {
            boolean maxSize = true;
            if (content.length>=this.maxSize)
                maxSize = false;
            booleans.add(maxSize);
        }

        if(this.minSize!=0) {
            boolean minSize = true;
            if (content.length<=this.minSize)
                minSize = false;
            booleans.add(minSize);
        }

        if(!booleans.contains(false)) this.foundFiles.add(file);

        return FileVisitResult.CONTINUE;
    }


    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    public List<Path> getFoundFiles() {
        return foundFiles;
    }



}

