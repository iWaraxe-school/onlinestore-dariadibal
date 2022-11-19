package by.issoft;

public class Product {
    private String name;
    private Double price;
    private Double rate;

    public Product(String name, Double price, Double rate) {
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    @Override
    public String toString() {
        String productInfo = String.format("Name:'%s', Price: %s,Rate: %s", name, price, rate);
        return productInfo;
    }
}
