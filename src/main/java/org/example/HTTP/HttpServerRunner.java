package org.example.HTTP;

public class HttpServerRunner {
    static void main() {
        HttpServer server = new HttpServer(8082);
        server.run();
    }
}
