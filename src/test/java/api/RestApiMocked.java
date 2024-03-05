package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static javax.management.Query.and;

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
    @ParameterizedTest
    @ValueSource(ints = {1,5,9,10})
    public void getOrdersByIdAndCheckResponseCodeIsOk(int orderId) {
        int responseOrderId = given().
                log()
                .all()
                .when()
                .get("/test-orders/" + orderId)
//                .get("/test-orders/{orderId}", orderId)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .path("id");
        Assertions.assertEquals(orderId, responseOrderId);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {-1,0})
    public void getOrdersByIdAndCheckResponseCodeIsNotOk(int orderId) {
        given().
                log()
                .all()
                .when()
                .get("/test-orders/" + orderId)
//                .get("/test-orders/{orderId}", orderId)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    // 10 Homework. Create parameterized tests to validate the endpoint and pass username and password as query

    @ParameterizedTest
    @CsvSource({
            "username, password",
            "username1, password1",
            "username!, password1!",
            "username1#, password1!",
            "Username1!, Password1!",
            "USER_NAME, PASS_WORD",
            "12345, 6789"
    })
    public void testEndpointWithCsvSourceGetResponseIsOk(String username, String password) {
        String response = given().
                log()
                .all()
                .when()
                .get("/test-orders?username=" + username+"&password="+ password)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .path("apiKey");

        Assertions.assertNotNull(response);

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

    /*// 9 homework DELETE method
    // 9.1 Positive test with valid data (with valid id which contains valid credentials)
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

    // 9.2 Negative test with invalid data which contains incorrect credentials(400 status code, bad request)
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

    // Negative scenario for not supported requests, when slash and id value are missing (405 status code. Request is not supported by the target resource)
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

    // Negative scenario for the Order that was not found, not stored in the database (404 Status code)
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
    }*/
    // 9.1.1 Positive test with valid data (with valid id which contains valid credentials) with HttpStatus
    @Test
    public void deleteOrderByIdCheckResponseHttpCodeIsOk() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/5")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    // 9.2.1 Negative test with invalid data which contains incorrect credentials(400 status code, bad request) with HttpStatus
    @Test
    public void deleteOrderByIdCheckResponseHttpCodeIsBadRequest() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/3fa85f64-5717-4562-b3fc-2c963f66afa6")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    // Negative scenario for not supported requests, when slash and id value are missing (405 status code. Request is not supported by the target resource) with HttpStatus
    @Test
    public void deleteOrderByIdCheckResponseHttpCodeIsNotSupported() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    // Negative scenario for the Order that was not found, not stored in the database (404 Status code) with HttpStatus
    @Test
    public void deleteOrderByIdCheckResponseHttpCodeOrderIsNotFound() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/001")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    // Negative scenario for the unauthorized user (401 Status code) with HttpStatus
    @Test
    public void deleteOrderByIdCheckResponseHttpCodeIsNotUnauthorized() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "12345")
                .delete("/test-orders/1")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

}
