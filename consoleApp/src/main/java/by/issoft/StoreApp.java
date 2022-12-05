package by.issoft;

import by.issoft.helper.StoreHelper;
import by.issoft.store.Store;

import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {
        Store onlineStore = new Store();
        StoreHelper storeHelper = new StoreHelper(onlineStore);
        storeHelper.fillStoreRandomly();
        onlineStore.printAllCategoriesAndProducts();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please use the following commands to interact with the store: sort, top, quit.");

            switch (scanner.nextLine()) {
                case "sort":
                    System.out.println("Sorted list:");
                    onlineStore.printSorted();
                    break;
                case "top":
                    System.out.println("Top 5: ");
                    onlineStore.printTop5();
                    break;
                case "quit":
                    scanner.close();
                    System.out.println("Bye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong command. Try again.");
                    break;
            }
        }
    }
}
