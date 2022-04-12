package by.issoft.store.helper;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import javassist.tools.reflect.Reflection;
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

    public void fillStoreRandomly() {
        RandomStorePopulator populator = new RandomStorePopulator();
        Map<Category, Integer> categoryMap = createCategoryMap();

        for (Map.Entry<Category, Integer> entry: categoryMap.entrySet()) {
            for (int i=0; i < entry.getValue(); i++) {
                Product product = new Product(
                        populator.getProductName(entry.getKey().getName()),
                        populator.getPrice(),
                        populator.getRate());
                entry.getKey().addProductToCategory(product);
            }
            this.store.addCategory(entry.getKey());
        }
    }
    private static Map<Category,Integer> createCategoryMap() {
        Map<Category,Integer> categoryToPut = new HashMap<>();

        Reflections reflections = new Reflections("by.issoft.domain.categories");

        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type:subTypes) {
            try {
                Random random = new Random();
                categoryToPut.put(type.getConstructor().newInstance(),random.nextInt(10));

            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return categoryToPut;
    }
}
