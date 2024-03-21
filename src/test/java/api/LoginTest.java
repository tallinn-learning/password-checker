package api;

import com.google.gson.Gson;
import dto.LoginDto;
import dto.OrderDtoMocked;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import utils.RandomDataGenerator;

import static io.restassured.RestAssured.given;
import static javax.management.Query.not;

public class LoginTest {
    // Login with authorization
    @Test
    public void SuccessfulLoginWithCorrectLoginAndPassword() {

        LoginDto loginDto = new LoginDto("maria", "p32V7aYbfT2n"); // own data with username and password


        String response = given()
                .header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .body(new Gson().toJson(loginDto))
                .post("http://35.208.34.242:8080/login/student")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();

    }

    // test for unauthorized user
    @Test
    public void UnSuccessfulLoginWithIncorrectLoginAndPassword() {

        LoginDto loginDto = new LoginDto("username", "");

        String response = given()
                .header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .body(new Gson().toJson(loginDto))
                .post("http://35.208.34.242:8080/login/student")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .asString();

    }

    // View orders without authorization - negative scenario
    @Test
    public void UnsuccessfulOrderViewWithoutAuthorization() {

        LoginDto loginDto = new LoginDto("maria", "");

        given()
                .header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .body(new Gson().toJson(loginDto))
                .get("http://35.208.34.242:8080/orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);


    }

    //    // View orders after authorization but with no orders - positive scenario
    @Test
    public void SuccessfulOrderViewWithoutOrders() {
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", AuthHeader())
                .log()
                .all()
                .get("http://35.208.34.242:8080/orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);

    }
// Authorization in advance, not to make each time for each test
    private String AuthHeader() {
        LoginDto loginDto = new LoginDto("maria", "p32V7aYbfT2n");

        String response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(new Gson().toJson(loginDto))
                .post("http://35.208.34.242:8080/login/student")
                .then()
                .extract()
                .asString();

        return "Bearer " + response;
    }

    // Create a new order with corresponding data - positive scenario
    @Test
    public void SuccessfulOrder() {
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", AuthHeader())
                .body("{\"status\": \"OPEN\", \"courierId\": 0, \"customerName\": \"Olga\", \"customerPhone\": \"111111\", \"comment\": \"new order\", \"id\": 1}")
                .log()
                .all()
                .when()
                .post("http://35.208.34.242:8080/orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();

    }
    // Get order by id - positive scenario
    @Test
    public void getOrderById() {
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", AuthHeader())
                .log()
                .all()
                .when()
                .get("http://35.208.34.242:8080/orders/")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
    }

    // Delete order by id - positive scenario
    @Test
    public void deleteOrderById() {
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", AuthHeader())
                .log()
                .all()
                .when()
                .delete("http://35.208.34.242:8080/orders/268")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();


    }

    }
