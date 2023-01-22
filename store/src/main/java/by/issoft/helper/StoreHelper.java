package by.issoft.helper;


import by.issoft.domain.Category;
import by.issoft.domain.GeneralProductBuilder;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class StoreHelper {
    Store store;

    public StoreHelper(Store store) {
        this.store = store;
    }

    public void fillStoreRandomly() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomStorePopulator populator = new RandomStorePopulator();
        Map<Category, Integer> categoryMap = createCategoryMap();

        for (Map.Entry<Category, Integer> entry : categoryMap.entrySet()) {
            // insert into DB category (entry.key)
            for (int i = 0; i < entry.getValue(); i++) {
                // insert products into DB
                GeneralProductBuilder builder = new GeneralProductBuilder();
                Product newproduct = builder.name(populator.getProductName(entry.getKey().getName()))
                        .price(populator.getPrice())
                        .rate(populator.getRate())
                        .build();
                // Not relevant
                entry.getKey().addProductToCategory(newproduct);
            }
            // Not relevant
            this.store.addCategory(entry.getKey());
        }
    }

    private static Map<Category, Integer> createCategoryMap() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map<Category, Integer> categoryToPut = new HashMap<>();

        Reflections reflections = new Reflections("by.issoft.domain.categories");

        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type : subTypes) {
            Random random = new Random();
            categoryToPut.put( CategoryFactory.getCategory(type), random.nextInt(10));
        }
        return categoryToPut;
    }
}
