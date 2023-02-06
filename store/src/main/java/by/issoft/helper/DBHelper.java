package by.issoft.helper;

import by.issoft.domain.Category;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DBHelper {
    static ResultSet RESULTSET = null;
    private DB db = DB.getInstance();

    public void createCategoryTable(){
        String query = "CREATE TABLE IF NOT EXISTS CATEGORIES(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NAME VARCHAR(255) NOT NULL);";
        db.create(query);
    }
    public void createProductTable(){
        String query = "CREATE TABLE IF NOT EXISTS PRODUCTS(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "CATEGORY_ID INT NOT NULL, NAME VARCHAR(255) NOT NULL, RATE DECIMAL (10, 1) NOT NULL, " +
                "PRICE DECIMAL (10, 1) NOT NULL, FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID));";
        db.create(query);
    }

    public void fillStoreRandomly() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomStorePopulator populator = new RandomStorePopulator();
        Map<Category, Integer> categoryMap = createCategoryMap();
        int index =1;
        for (Map.Entry<Category, Integer> entry : categoryMap.entrySet()) {
            addCategoryToDB(entry.getKey());
            addProductToDB(entry, populator,index);
            index++;
        }
    }

    @SneakyThrows
    private void addCategoryToDB(Category category) {
        PreparedStatement insertCategories = DB.CONNECTION.prepareStatement("INSERT INTO CATEGORIES(NAME)"
                + " VALUES (?)");
        insertCategories.setString(1, category.getName());
        db.execute(insertCategories);
    }

    @SneakyThrows
    private void addProductToDB(Map.Entry<Category, Integer> entry, RandomStorePopulator populator, int categoryID ){
        for (int i = 0; i < entry.getValue(); i++) {
            PreparedStatement insertProducts = DB.CONNECTION.prepareStatement("INSERT INTO PRODUCTS (CATEGORY_ID,NAME,RATE,PRICE)"
                    + "VALUES (?,?,?,?)");
            insertProducts.setInt (1, categoryID);
            insertProducts.setString (2,populator.getProductName(entry.getKey().getName()));
            insertProducts.setDouble (3,populator.getRate());
            insertProducts.setDouble (4,populator.getPrice());
            db.execute(insertProducts);
        }
    }

    private static Map<Category, Integer> createCategoryMap() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map<Category, Integer> categoryToPut = new HashMap<>();

        Reflections reflections = new Reflections("by.issoft.domain.categories");

        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type : subTypes) {
            Random random = new Random();
            categoryToPut.put( CategoryFactory.getCategory(type), random.nextInt(10) + 1);
        }
        return categoryToPut;
    }

    @SneakyThrows
    public void printFilledStore() {
        try {
            System.out.println("\nPrint Store from DataBase\n");
            RESULTSET = db.execute("SELECT * FROM CATEGORIES");
            System.out.println("List of Categories");
            while (RESULTSET.next()) {
                System.out.println(
                        RESULTSET.getInt("ID") + ", " +
                                RESULTSET.getString("NAME"));

            }
            RESULTSET = db.execute("SELECT * FROM PRODUCTS");
            System.out.println("List of Products");
            while (RESULTSET.next()) {
                System.out.println(
                        RESULTSET.getInt("CATEGORY_ID") + ", " +
                                RESULTSET.getString("NAME") + ", " +
                                RESULTSET.getDouble("RATE") + ", " +
                                RESULTSET.getDouble("PRICE"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            RESULTSET.close();
        }
    }

}
