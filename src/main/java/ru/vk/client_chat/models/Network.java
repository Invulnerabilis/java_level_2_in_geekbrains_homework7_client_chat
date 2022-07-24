package ru.vk.client_chat.models;

import ru.vk.client_chat.controllers.ChatController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 8888;
    private final String host;
    private final int port;
    private DataOutputStream out;
    private Socket socket;
    private DataInputStream in;

    private ChatController chatController;

    public Network() {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public Network(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Connection not established");
        }
    }

    public String sendMessage(String message) {
        try {
            out.writeUTF(message);
            return message;
        } catch (IOException e) {
            e.printStackTrace();
            String errMessage = "Error sending message:" + e.getMessage();
            System.out.println(errMessage);
            return errMessage;
        }
    }

    public void startWaitingMessages(ChatController chatController) {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();
                    chatController.appendMessage("Server: " + message);
                }
            } catch (IOException e) {
                try {
                    closeSession();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        t.start();
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public void closeSession() throws IOException {
        out.close();
        in.close();
        socket.close();
    }
}
