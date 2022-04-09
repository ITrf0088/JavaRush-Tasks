package com.javarush.task.task20.task2022;


import java.io.*;
/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    String filename;
    public Solution(String fileName) throws FileNotFoundException {
        this.filename = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(filename,true);

    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException {
        Solution solution  = new Solution("E:/text1.txt");
        solution.writeObject("hello world");

        FileOutputStream out = new FileOutputStream("E:/text1.txt");
        solution.writeObject(new ObjectOutputStream(out));


    }
}
