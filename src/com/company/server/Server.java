package com.company.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Server implements SendMessage {
    private final List<Socket> sockets;

    public Server() {
        sockets = new LinkedList<>();
    }


    public static void main(String[] args) throws IOException {
        new Server().run();
    }


    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            Socket socket = serverSocket.accept();
            sockets.add(socket);

            new ListenMessageClient(socket, this).start();
        }
    }

    @Override
    public void sendMessage(Socket sender,final String message) {
        System.out.println(message);

        for (Socket socket : sockets){
            if (!socket.isConnected()){
                sockets.remove(socket);
            }
        }

        for (Socket socket : sockets){
            if (socket == sender){
                continue;
            }

            try {
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
