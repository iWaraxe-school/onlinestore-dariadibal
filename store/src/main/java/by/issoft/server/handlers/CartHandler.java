package by.issoft.server.handlers;

import by.issoft.store.OrderCreationExecutor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CartHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream os = exchange.getResponseBody();
        String response ="";
        ExecutorService executor = Executors.newFixedThreadPool(10);

        switch (exchange.getRequestMethod().toLowerCase(Locale.ROOT)){
            case "post":
                OrderCreationExecutor oce = new OrderCreationExecutor();
                executor.submit(oce);
                response = "Product is added to the cart";
                exchange.sendResponseHeaders(200, response.length());
                break;
            case "get":
                response = "Return list of products";
                exchange.sendResponseHeaders(200, response.length());
                break;
            case "delete":
                response = "Product is deleted from cart";
                exchange.sendResponseHeaders(200, response.length());
                break;
            default:
                response = "Method Not Allowed";
                exchange.sendResponseHeaders(405, response.length());
                break;

        }
        os.write(response.getBytes());
        os.close();
    }
}
