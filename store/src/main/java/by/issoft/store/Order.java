package by.issoft.store;

import by.issoft.domain.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private List<Product> products;
    UUID orderNumber;

    public Order() {
        this.orderNumber = UUID.randomUUID();
        this.products = new ArrayList<>();
    }

    public void add (Product product) {
        products.add(product);
    }
}
