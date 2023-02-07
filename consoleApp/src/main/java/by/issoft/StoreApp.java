package by.issoft;

import by.issoft.helper.DB;
import by.issoft.helper.DBHelper;
import by.issoft.helper.StoreHelper;
import by.issoft.server.Client;
import by.issoft.server.Server;
import by.issoft.store.OrderCreationExecutor;
import by.issoft.store.Store;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.concurrent.*;


public class StoreApp {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException, ExecutionException {
        DB db = DB.getInstance();
        db.connectToDb();
        db.clearDB();

        DBHelper dbHelper = new DBHelper();
        dbHelper.createCategoryTable();
        dbHelper.createProductTable();
        dbHelper.fillStoreRandomly();
        dbHelper.printFilledStore();

        Store onlineStore = Store.getInstance();
        StoreHelper storeHelper = new StoreHelper();
        storeHelper.fillStore();
        onlineStore.printAllCategoriesAndProducts();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        ScheduledExecutorService scheduledES = Executors.newSingleThreadScheduledExecutor();
        scheduledES.scheduleAtFixedRate(() -> {
            onlineStore.cleanPurchased();
        }, 0, 2, TimeUnit.MINUTES);

        Server server = new Server();
        server.createServer();

        Client client = new Client();
        client.printCategory();
        client.addToCart();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please use the following commands to interact with the store: sort, top, order, quit.");

            switch (scanner.nextLine()) {
                case "sort":
                    System.out.println("Sorted list:");
                    onlineStore.printSorted();
                    break;
                case "top":
                    System.out.println("Top 5: ");
                    onlineStore.printTop5();
                    break;
                case "order":
                    OrderCreationExecutor oce = new OrderCreationExecutor();
                    executor.submit(oce);
                    System.out.println("Order Created");
                    break;
                case "quit":
                    scanner.close();
                    System.out.println("Bye");
                    executor.shutdown();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Wrong command. Try again.");
                    break;
            }
        }
    }
}
