package by.issoft.helper;


import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StoreHelper {
    Store store = Store.getInstance();
    DB db = DB.getInstance();

    @SneakyThrows
    public void fillStore () {
        ResultSet RESULTSET = db.execute("SELECT * FROM CATEGORIES");
        List<Category> categories = new ArrayList<>();
        while (RESULTSET.next()) {
            categories.add(new Category(RESULTSET.getString("NAME")));
        }

        for (Category category: categories) {
            RESULTSET = db.execute("SELECT PRODUCTS.NAME, PRODUCTS.RATE, PRODUCTS.PRICE FROM PRODUCTS INNER JOIN CATEGORIES " +
                    "ON PRODUCTS.CATEGORY_ID=CATEGORIES.ID AND CATEGORIES.NAME ='"+ category.getName() +"'");
            while (RESULTSET.next()) {
                category.addProductToCategory(new Product(RESULTSET.getString("NAME"),
                        RESULTSET.getDouble("RATE"),
                        RESULTSET.getDouble("PRICE")));
            }
        }

        for (Category category: categories) {
            store.addCategory(category);
        }
   }
}