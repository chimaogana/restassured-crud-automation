package users;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserCrudTest {
    @Test
    void shouldGetUser(){
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when().get("/users/1").then().statusCode(200)
                .body("id",equalTo (1))
                .body("name",equalTo("Leanne Graham"))
                .body("username",equalTo("Bret"));
    }

    @Test
    void shouldCreateUser(){

        String requestBody = """
        {
            "name": "Chima Ogana",
            "username": "chima",
            "email": "morgan.ogana.c@gmail.com"
        }
        """;
        given().contentType("application/json").body(requestBody).baseUri("https://jsonplaceholder.typicode.com")
                .when().post("/users")
                .then().statusCode(201).body("name", equalTo("Chima Ogana")).body("username", equalTo("chima")).body("email", equalTo("morgan.ogana.c@gmail.com"));
    }

    @Test
    void ShouldUpdateUser(){
        String requestBody = """
        {
            "name": "Chimas Ogana"
         
        }
        """;
        given().baseUri("https://jsonplaceholder.typicode.com").body(requestBody).contentType("application/json")
                .when().put("/users/1").then().body("name",equalTo("Chimas Ogana"));
    }

    @Test
    void ShouldDeleteUser(){
        given().baseUri("https://jsonplaceholder.typicode.com").contentType("application/json")
                .when().delete("/users/1").then().statusCode(200);
    }
}
