package com.javarush.task.task30.task3008;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.*;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> sendMsg : connectionMap.entrySet()) {
            try {
                sendMsg.getValue().send(message);
            } catch (IOException e) {
                System.out.println(sendMsg.getKey() + " мы не смогли отправить вам сообщение!");
            }
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());
        System.out.println("Cервер запущен");
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e) {
            e.getMessage();
            serverSocket.close();
        }

    }

    private static class Handler extends Thread {
        Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException {
            String name;
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message getMsg = null;
                try {
                    getMsg = connection.receive();
                    if (getMsg.getType() != MessageType.USER_NAME ||
                            getMsg.getData().equals("") ||
                            connectionMap.containsKey(getMsg.getData())) continue;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                connectionMap.put(getMsg.getData(), connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                name = getMsg.getData();
                break;
            }
            return name;
        }

        private void notifyUsers(Connection connection, String userName) {
            for (Map.Entry<String, Connection> connectMap : connectionMap.entrySet()) {
                if (connectMap.getKey().equals(userName)) continue;
                try {
                    connection.send(new Message(MessageType.USER_ADDED, connectMap.getKey()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String msg = (userName + ": " + message.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, msg));

                } else
                    ConsoleHelper.writeMessage("Ошибка не явлется текстом!");
            }

        }

        @Override
        public void run() {

            System.out.println("Установленно соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
            String name = "";
            try (Connection connection = new Connection(this.socket)) {
                name = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));
                notifyUsers(connection, name);
                serverMainLoop(connection, name);
                } catch (IOException  e) {
                    ConsoleHelper.writeMessage("IOException! При обмене данными с удаленным адресом");
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e){
                    ConsoleHelper.writeMessage("ClassNotFoundException! При обмене данными с удаленным адресом");
                    e.printStackTrace();
                }
                finally {
                    if (!name.isEmpty()) {
                        connectionMap.remove(name);
                        sendBroadcastMessage(new Message(MessageType.USER_REMOVED, name));
                    }
                    System.out.println("Cоединение с удаленным адресом закрыто");
                }

            }
        }

    }


