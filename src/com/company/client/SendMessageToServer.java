package com.company.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Random;

public class SendMessageToServer extends Thread {
    private final Socket socket;
    private final String label;
    private final Random random;

    public SendMessageToServer(Socket socket, String label) {
        this.socket = socket;
        this.label = label;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (true) {
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF("This is message from " + label + " (" + LocalTime.now() + " )");

                Thread.sleep(5000 + random.nextInt(10000));

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
