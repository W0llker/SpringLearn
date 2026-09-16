package org.example.SocketTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServer {
    static void main() throws IOException {
        try (var serverSocket = new ServerSocket(8081);
             var clientSocket = serverSocket.accept();
             var inputStream = new DataInputStream(clientSocket.getInputStream());
             var outputStream = new DataOutputStream(clientSocket.getOutputStream());
             var scanner = new Scanner(System.in)) {
            var request = inputStream.readUTF();
            while (!request.equals("stop")) {
                System.out.println("Клиент: " + request);
                outputStream.writeUTF(scanner.nextLine());
                request = inputStream.readUTF();
            }
        }
    }
}
