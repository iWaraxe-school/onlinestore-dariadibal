package by.issoft.helper;

import by.issoft.domain.Category;
import by.issoft.domain.categories.BookCategory;
import by.issoft.domain.categories.FoodCategory;

import java.lang.reflect.InvocationTargetException;

public class CategoryFactory {
    public static Category getCategory (Class<? extends Category> category) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Category toReturn = null;
        String name = category.getConstructor().newInstance().getClass().getSimpleName();

        switch (name) {
            case "BookCategory":
                toReturn = new BookCategory();
                break;
            case "FoodCategory":
                toReturn = new FoodCategory();
                break;
            default:
                throw new IllegalArgumentException("Wrong product type:" + name);
        }
        return toReturn;
    }
}
