package com.company.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ListenMessageClient extends Thread {
    private final Socket client;
    private final SendMessage sendMessage;

    public ListenMessageClient(Socket socket, SendMessage sendMessage){
        this.client = socket;
        this.sendMessage = sendMessage;
    }

    @Override
    public void run() {
        // Listening form clients
        while (true){
            try {
                DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
                String message = dataInputStream.readUTF();

                sendMessage.sendMessage(client, message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
