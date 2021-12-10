import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import main.factories.httphandler.CreateUserHttpHandlerFactory;

public class App {
    public static void main(String[] args) throws Exception {
        int port = 4000;

        InetSocketAddress netSocket = new InetSocketAddress(port);
        HttpServer httpServer = HttpServer.create(netSocket, 0);

        httpServer.createContext("/user", CreateUserHttpHandlerFactory.create());

        httpServer.setExecutor(null);

        httpServer.start();
        System.out.println("Server run on " + port);
    }
}
