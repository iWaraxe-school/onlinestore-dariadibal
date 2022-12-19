package by.issoft.domain;

public interface ProductBuilder {
    public ProductBuilder name(String name);

    public ProductBuilder price(Double price);

    public ProductBuilder rate(Double rate);

    public Product build();
}
