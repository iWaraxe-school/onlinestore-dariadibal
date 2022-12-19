package by.issoft.domain;

public class GeneralProductBuilder implements ProductBuilder {
    private String name;
    private Double price;
    private Double rate;

    public GeneralProductBuilder() {
        super();
    }

    @Override
    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ProductBuilder price(Double price) {
        this.price = price;
        return this;
    }

    @Override
    public ProductBuilder rate(Double rate) {
        this.rate = rate;
        return this;
    }

    @Override
    public Product build() {
        Product product = new Product(name, price, rate);
        return product;
    }

}
