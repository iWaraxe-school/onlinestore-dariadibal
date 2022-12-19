package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.ProductComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private static Store instance;
    private Store(){}
    public static Store getInstance(){
        if(instance == null) {
            instance = new Store();
        }
        return instance;
    }
    private List<Category> categoryList = new ArrayList<>();

    public void addCategory(Category category) {
        categoryList.add(category);
    }

    public void printAllCategoriesAndProducts() {
        for (Category category : categoryList) {
            category.printAllProducts();
        }
    }

    private List<Product> getListOfProducts() {
        return categoryList.stream()
                .map(Category::getProductList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void printSorted() {
        List<Product> list = getListOfProducts();
        list.sort(ProductComparator.generalComparator);
        list.forEach(System.out::println);
    }

    public void printTop5() {
        getListOfProducts().stream()
                .sorted(ProductComparator.top5Comparator)
                .limit(5)
                .forEach(System.out::println);
    }
}
