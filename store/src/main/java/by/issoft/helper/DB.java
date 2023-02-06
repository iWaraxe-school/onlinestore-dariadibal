package by.issoft.helper;

import lombok.SneakyThrows;

import java.sql.*;

public class DB {
    private static DB instance;
    private DB(){}

    public static DB getInstance(){
        if(instance == null) {
            instance = new DB();
        }
        return instance;
    }

    static Connection CONNECTION = null;
    static Statement STATEMENT = null;
    static Statement STATEMENT_ENCLOSED = null;

    static final String URL = "jdbc:h2:mem:testdb";
    static final String USERNAME = "student";
    static final String PASSWORD = "student";

    @SneakyThrows
    public void connectToDb() {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("\nDatabase connection successfull!\n");
            STATEMENT = CONNECTION.createStatement();
            STATEMENT_ENCLOSED = CONNECTION.createStatement();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearDB() {
        String query1 = "DROP TABLE IF EXISTS CATEGORIES";
        String query2 = "DROP TABLE IF EXISTS PRODUCT";
        try {
            STATEMENT.executeUpdate(query1);
            System.out.println("categories dropped");
            STATEMENT.executeUpdate(query2);
            System.out.println("products dropped");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public void create(String sql) {
        try {
            System.out.println(sql);
            STATEMENT.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public ResultSet execute(String sql) {
        ResultSet RESULTSET = null;
        try {
            System.out.println(sql);
            RESULTSET = STATEMENT.executeQuery(sql);
            return RESULTSET;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @SneakyThrows
    public void execute(PreparedStatement sql) {
        try {
            System.out.println(sql);
            sql.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
