package com.company.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    private final Socket socket;
    private final String label;

    public Client(Socket socket, String label){
        this.socket = socket;
        this.label = label;
    }


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        String name = args[0];

        // Listening form server
        Client client = new Client(socket, name);
        client.start();

        // Send message to server
        Scanner scanner = new Scanner(System.in);
        while (true){
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.print("Enter message: ");
            String message = scanner.nextLine();
            dataOutputStream.writeUTF(message + " ( send by " + client.label + ")");
        }
    }


    @Override
    public void run() {
        // Listening form server
        while (true){
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String message = dataInputStream.readUTF();
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
