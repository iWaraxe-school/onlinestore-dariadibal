package by.issoft.helper;

import com.github.javafaker.Faker;

public class RandomStorePopulator {
    private Faker faker = new Faker();

    public String getProductName(String categoryName) {
        switch (categoryName) {
            case "Food":
                return faker.food().ingredient();
            case "Book":
                return faker.book().title();
            default:
                return null;
        }
    }

    public double getPrice() {
        return faker.number().randomDouble(2, 1, 100);
    }

    public double getRate() {
        return faker.number().randomDouble(1, 0, 5);
    }
}
