package api;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RestApiMocked {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://35.208.34.242";
        RestAssured.port = 8080;
    }


    @Test
    public void getOrderByIdCheckResponseCodeIsOk() {
        given()
                .log()
                .all()
                .when()
                .get("/test-orders/5")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void getOrderByInvalidCheckResponseCodeIsNotOkIsBadRequest() {
        get("http://35.208.34.242:8080/test-orders/11")
                .then()
                .statusCode(400);
    }

    @Test
    public void getOrderByInvalidCheckResponseCodeIsNotOkIsBadRequestTheSecondTest() {
        given().
                when().
                get("http://35.208.34.242:8080/test-orders/11")
                .then()
                .statusCode(400);
    }

    @Test
    public void getAllOrdersResponseCodeIsOk() {
        given().
                when().
                get("http://35.208.34.242:8080/test-orders/get_orders")
                .then()
                .statusCode(200);
    }

    // 9 homework DELETE method
    // 9.1 Positive test with valid data
    @Test
    public void deleteOrderByIdCheckResponseCodeIsOk() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/5")
                .then()
                .log()
                .all()
                .statusCode(204);
    }

    // 9.2 Negative test with invalid data (400 status code, bad request)
    @Test
    public void deleteOrderByIdCheckResponseCodeIsBadRequest() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/3fa85f64-5717-4562-b3fc-2c963f66afa6")
                .then()
                .log()
                .all()
                .statusCode(400);
    }

    // Negative scenario for not supported requests (405 status code. Request is not supported by the target resource)
    @Test
    public void deleteOrderByIdCheckResponseCodeIsNotSupported() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders")
                .then()
                .log()
                .all()
                .statusCode(405);
    }

    // Negative scenario for the Order that was not found (404 Status code)
    @Test
    public void deleteOrderByIdCheckResponseCodeOrderIsNotFound() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/001")
                .then()
                .log()
                .all()
                .statusCode(404);
    }

    // Negative scenario for the unauthorized user (401 Status code)
    @Test
    public void deleteOrderByIdCheckResponseCodeIsNotUnauthorized() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "12345")
                .delete("/test-orders/1")
                .then()
                .log()
                .all()
                .statusCode(401);
    }

}
