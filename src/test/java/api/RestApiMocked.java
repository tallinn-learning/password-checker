package api;

import com.google.gson.Gson;
import dto.OrderDtoMocked;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.RandomDataGenerator;

import static io.restassured.RestAssured.*;

public class RestApiMocked {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://35.208.34.242";
        RestAssured.port = 8080;
    }

    @Test
    public void createOrderAndCheckResponseCodeIsOk() {
        //OrderDtoMocked orderDtoMocked= new OrderDtoMocked("OPEN",0,"customer","56473256","hello",0);
        OrderDtoMocked orderDtoMocked = new OrderDtoMocked();

        // If Public OrderDtoMocked.comment="comm";


        orderDtoMocked.setStatus("OPEN");
        orderDtoMocked.setCourierId(0);
        orderDtoMocked.setCustomerName(RandomDataGenerator.generateName());
        orderDtoMocked.setCustomerPhone("4563623782");
        orderDtoMocked.setComment("comment");
        orderDtoMocked.setId(1);

        given()
                .header("Content-type", "application/json")
                .log()
                .all()
                .when()
                .body(new Gson().toJson(orderDtoMocked))
                .post("/test-orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getOrderByIdAndCheckResponseCodeIsOk() {
        get("/test-orders/9")
                .then()
                .statusCode(200);
    }

    @Test
    public void getOrderByInvalidIdAndCheckResponseCodeIsBadRequest() {
        given().
                when().
                get("/test-orders/11")
                .then()
                .statusCode(400);
    }

    @Test
    public void getGetAllOrdersAndCheckResponseCodeIsOk() {
        get("/test-orders/get_orders")
                .then()
                .statusCode(200);
    }
    /* @Test
    public void createOrderAndDeleteIt() {
        // Определите тело запроса (возможно, вам нужно передать данные о заказе в формате JSON)
        String requestBody = "{\"key1\":\"value1\", \"key2\":\"value2\"}";

        // Отправьте запрос на создание заказа с корректными заголовками и телом запроса
        int orderId = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all() // для логирования запроса
                .when()
                .post("/test-orders")
                .then()
                .log().all() // для логирования ответа
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("id");

        // Удалите заказ по его ID
        given()
                .log().all() // для логирования запроса
                .when()
                .delete("/test-orders/" + orderId)
                .then()
                .log().all() // для логирования ответа
                .statusCode(HttpStatus.SC_OK);
    } */

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 9, 10})
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
    @ValueSource(ints = {0, 11})
    public void getOrdersByIdAndCheckBadRequestCode(int orderId) {
        given().
                log()
                .all()
                .when()
                .get("/test-orders/" + orderId)
                //.get("/test-orders/{orderId}", orderId)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    // Homework N9
    @Test
    public void deleteOrderByIdAndCheckResponseHttpStatusIsNoContent() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/1")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void deleteOrderWithoutApiKeyAndCheckResponseHttpStatusIsBadRequest() {
        given()
                .log()
                .all()
                .when()
                .delete("/test-orders/4")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void deleteOrderWithWrongApiKeyLengthAndCheckResponseHttpStatusIsUnauthorized() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234566436346347890123456")
                .delete("/test-orders/4")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void deleteOrderByLowerIdThanExpectedAndCheckResponseHttpStatusIsBadRequest() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/0")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void deleteOrderByBiggerIdThanExpectedAndCheckResponseHttpStatusIsBadRequest() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/11")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void deleteOrderByLetterIdAndCheckResponseHttpStatusIsBadRequest() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/one")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void deleteOrderWithoutIdAndCheckResponseHttpStatusIsNotAllowed() {
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }


}
