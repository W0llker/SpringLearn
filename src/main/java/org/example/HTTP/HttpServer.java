package org.example.HTTP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServer {
    private int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void run() {
        try {
            var severSocket = new ServerSocket(port);
            var socket = severSocket.accept();
            processSocket(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processSocket(Socket socket) {
        try (socket;
             var inputStream = new DataInputStream(socket.getInputStream());
             var outputStream = new DataOutputStream(socket.getOutputStream())) {
            System.out.println(new String(inputStream.readNBytes(100)));
            byte[] bytes = Files.readAllBytes(Path.of("src/main/resources/example.html"));
            outputStream.write(("" +
                    "HTTP/1.1 200 OK" +
                    "content-type: text/html" +
                    "content-length: %s" +
                    "").formatted(bytes.length).getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
