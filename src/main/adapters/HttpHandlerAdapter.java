package main.adapters;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import presentation.contracts.Controller;
import presentation.contracts.Request;
import presentation.contracts.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class HttpHandlerAdapter implements HttpHandler {
    Controller controller;

    public HttpHandlerAdapter(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            InputStream inputStream = exchange.getRequestBody();
            StringBuilder requestBodyBuilder = new StringBuilder();
            int i;
            while ((i = inputStream.read()) != -1) {
                requestBodyBuilder.append((char) i);
            }

            String requestBody = requestBodyBuilder.toString();

            requestBody = requestBody.replace("{", "");
            requestBody = requestBody.replace("}", "");

            String[] keysAndValues = requestBody.split(",");

            HashMap<String, String> body = new HashMap<>();
            for (String keyAndValue : keysAndValues) {
                String key = keyAndValue.split(":")[0].replace("\"", "").trim();
                String value = keyAndValue.split(":")[1].replace("\"", "").trim();
                body.put(key, value);
            }

            Request request = new Request();
            request.setBody(body);

            Response response = this.controller.handle(request);

            StringBuilder responseBodyBuilder = new StringBuilder();
            responseBodyBuilder.append("{");
            response.getBody().forEach((key, value) -> {
                responseBodyBuilder.append("\"" + key + "\": \"" + value + "\",");
            });
            responseBodyBuilder.append("}");

            String responseBody = responseBodyBuilder.toString();
            byte[] rawResponseBody = responseBody.getBytes();

            Headers headers = exchange.getResponseHeaders();
            headers.set("Content-Type", "application/json");

            exchange.sendResponseHeaders(200, rawResponseBody.length);

            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(rawResponseBody);

            exchange.close();
        } catch (Exception e) {
            throw new IOException();
        }
    }
}
