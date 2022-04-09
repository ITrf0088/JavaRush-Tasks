package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.Helper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(Helper.generateRandomString(),".temp");
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getFileSize(){
        try {
            return Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void putEntry(Entry entry){
        try {
            OutputStream out = Files.newOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            objectOut.writeObject(entry);
            out.close();
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Entry getEntry(){
        if(getFileSize() <=0) return null;
        Entry entry = null;
        try {
            InputStream in = Files.newInputStream(path);
            ObjectInputStream objectIn = new ObjectInputStream(in);
            entry = (Entry) objectIn.readObject();
            in.close();
            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entry;
    }

    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
