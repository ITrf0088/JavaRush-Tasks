package com.javarush.task.task40.task4005;


import java.io.*;
import java.net.Socket;

/* 
Сокетный сервер и клиент
*/

public class Connection implements Closeable {
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;

    public Connection(Socket socket) throws Exception {
        this.socket = socket;
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void send(String message) throws Exception {
        out.write(message);
        out.flush();
    }

    public String receive() throws Exception {
        return in.readLine();
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
