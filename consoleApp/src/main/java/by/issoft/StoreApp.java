package by.issoft;

import by.issoft.config.SortDirection;
import by.issoft.config.XMLParser;
import by.issoft.helper.StoreHelper;

import java.util.LinkedHashMap;

public class StoreApp {
    public static void main(String[] args) {
        Store onlineStore = new Store();
        StoreHelper storeHelper = new StoreHelper(onlineStore);
        storeHelper.fillStoreRandomly();
        onlineStore.printAllCategoriesAndProducts();

        LinkedHashMap<String, SortDirection> sortRules = XMLParser.getSortConfig();
    }
}
