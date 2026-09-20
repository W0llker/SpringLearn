package org.example.HTTP;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import static java.net.http.HttpRequest.BodyPublishers.ofFile;

public class HttpClientRunner {
    static void main() throws IOException, InterruptedException {
        var http = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8082"))
                .header("content-type", "application/json")
                .POST(ofFile(Path.of("src/main/resources/example.json")))
                .build();

        var responce = http.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(responce.headers());
        System.out.println(responce.body());
    }
}
