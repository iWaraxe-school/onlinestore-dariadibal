package by.issoft.server;

import by.issoft.server.handlers.CartHandler;
import by.issoft.server.handlers.CategoryHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public void createServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            var catH = server.createContext("/categories", new CategoryHandler());
            var cartH = server.createContext("/cart", new CartHandler());
            catH.setAuthenticator(new Authorization ("field"));
            cartH.setAuthenticator(new Authorization ("filed"));
            server.setExecutor(null);
            server.start();
            System.out.println("Server started");

        } catch (IOException e) {
            throw new RuntimeException("Error to build server", e);
        }
    }
}
