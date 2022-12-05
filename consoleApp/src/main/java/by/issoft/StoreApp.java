package by.issoft;

import by.issoft.helper.StoreHelper;
import by.issoft.store.Store;

public class StoreApp {
    public static void main(String[] args) {
        Store onlineStore = new Store();
        StoreHelper storeHelper = new StoreHelper(onlineStore);
        storeHelper.fillStoreRandomly();
        onlineStore.printAllCategoriesAndProducts();
    }
}
