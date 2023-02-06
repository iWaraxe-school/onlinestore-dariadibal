package by.issoft.server.handlers;

import by.issoft.store.Store;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class CategoryHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream os = exchange.getResponseBody();
        String response = "Method Not Allowed";
        if (exchange.getRequestMethod().toLowerCase().equals("get")) {
            response = "Store Categories: " + Store.getInstance().categoryNames();
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        } else {
            exchange.sendResponseHeaders(405, response.length());
            os.write(response.getBytes());
        }
        os.close();
        exchange.close();
    }
}
