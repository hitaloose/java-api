package main.decorators;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HttpHandlerErrorDecorator implements HttpHandler {
    HttpHandler httpHandler;

    public HttpHandlerErrorDecorator(HttpHandler httpHandler) {
        this.httpHandler = httpHandler;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            this.httpHandler.handle(exchange);
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{");
            stringBuilder.append("\"name\": \"" + e.toString() + "\",");
            stringBuilder.append("\"message\": \"" + e.getMessage() + "\",");

            stringBuilder.append("\"stack_trace\": [");
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                stringBuilder.append("\"" + stackTraceElement.toString() + "\",");
            }
            stringBuilder.append("]");
            stringBuilder.append("}");

            String responseBody = stringBuilder.toString();
            byte[] rawResponseBody = responseBody.getBytes();

            Headers headers = exchange.getResponseHeaders();
            headers.set("Content-Type", "application/json");

            exchange.sendResponseHeaders(500, rawResponseBody.length);

            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(rawResponseBody);

            exchange.close();
        }
    }
}
