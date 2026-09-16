package org.example.SocketTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Demo {
    static void main() throws IOException {

    }

    public static void demoSocket() throws IOException {
        try (var socket = new Socket("google.com", 80);
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var intputStream = new DataInputStream(socket.getInputStream())) {
            outputStream.writeUTF("Hello from client");
            var response = intputStream.readAllBytes();
            System.out.println(response.length);
        }
    }
}
