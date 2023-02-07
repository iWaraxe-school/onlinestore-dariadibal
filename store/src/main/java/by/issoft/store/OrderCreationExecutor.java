package by.issoft.store;

import by.issoft.domain.Product;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.List;
import java.util.Random;


import static java.util.concurrent.TimeUnit.*;

public class OrderCreationExecutor implements Runnable {
    private Store store = Store.getInstance();

    @SneakyThrows
    @Override
    public void run() {
        Random rand = new Random();
        int randomNumber = 31;
        Long timeout = rand.nextLong(randomNumber);

        System.out.println("OrderCreationExecutor started");
        Order order = new Order();
        List<Product> products = this.store.getListOfProducts();
        Collections.shuffle(products);
        Product randomProduct = products.get(0);
        order.add(randomProduct);
        System.out.println("Purchasing..." + order.orderNumber);
        SECONDS.sleep(timeout);
        store.addPurchasedProduct(randomProduct);
        System.out.println("OrderCreationExecutor ended");
    }
}
