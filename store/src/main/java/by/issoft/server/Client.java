package by.issoft.server;

import lombok.SneakyThrows;

import static io.restassured.RestAssured.given;


public class Client {
    @SneakyThrows
    public void printCategory() {
        String responseBody = given().auth()
                .basic("raccoon", "raccoon")
                .when()
                .get("/categories")
                .body()
                .asString();
        System.out.println("Client says: " + responseBody);
    }

    @SneakyThrows
    public void addToCart() {
        String responseBody = given().auth()
                .basic("raccoon", "raccoon")
                .when()
                .post("/cart")
                .body()
                .asString();
        System.out.println("Client says: " + responseBody);
    }
}
