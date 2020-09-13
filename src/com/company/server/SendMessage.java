package com.company.server;

import java.net.Socket;

public interface SendMessage {
    void sendMessage(Socket sender, String message);
}
