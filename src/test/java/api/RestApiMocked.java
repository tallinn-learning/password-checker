package api;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import dto.OrderDtoMocked;
import dto.OrderDtoMockedBuilderAndFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.RandomDataGenerator;

import static io.restassured.RestAssured.*;

public class RestApiMocked {

    @BeforeAll
    public static void setup() {
        baseURI = "http://35.208.34.242";
        port = 8080;
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
    // 11 Classwork
    @Test
    public void createOrderAndCheckResponseCodeIsOk() {
//      OrderDtoMocked orderDtoMocked = new OrderDtoMocked("OPEN", 0, "customer", "555553433", "hello", 0);

        OrderDtoMocked orderDtoMocked = new OrderDtoMocked();



        orderDtoMocked.setStatus("OPEN");
        orderDtoMocked.setComment("comment");
        orderDtoMocked.setCourierId(0);
        orderDtoMocked.setCustomerName(RandomDataGenerator.generateName());
        orderDtoMocked.setCustomerPhone(RandomDataGenerator.generateCustomerPhone()); // generated random phone number that contains only numerics
        orderDtoMocked.setComment(RandomDataGenerator.generateComment()); // generated random alphanumeric comment which length 50 characters
        orderDtoMocked.setId(1);

        given().
                header("Content-Type", "application/json")
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

    // 11 HOMEWORK test for PUT method with generated random values.
    @Test
    public void updateOrderAndCheckResponseCodeIsOk() {
        OrderDtoMocked orderDtoMocked = new OrderDtoMocked();

        orderDtoMocked.setStatus("OPEN");
        orderDtoMocked.setComment("comment");
        orderDtoMocked.setCourierId(0);
        orderDtoMocked.setCustomerName(RandomDataGenerator.generateName());
        orderDtoMocked.setCustomerPhone(RandomDataGenerator.generateCustomerPhone()); // generated random phone number that contains only numerics
        orderDtoMocked.setComment(RandomDataGenerator.generateComment()); // generated random alphanumeric comment which length 50 characters
        orderDtoMocked.setId(5);


        RestAssured.given()
               .baseUri("http://35.208.34.242:8080")
                .header("accept", "application/json")
                .header("api_key", "1234567890123456")
                .contentType(ContentType.JSON)
               .body(new Gson().toJson(orderDtoMocked))
                //.body(new Gson().toJson(orderDtoMocked))
                .put("/test-orders/5")
                //.put("/test-orders/{id}", orderDtoMocked.getId())  // no need to indicate twice id
                //.put()
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
               // .body(matchesJsonSchemaInClasspath("response-schema,json"));
                //.body() //(matchesJsonSchemaInClasspath("response-schema,json"));


    }
    /// 12 Classwork  with build
    @Test
    public void createOrderAndCheckResponseCodeIsOkWithBuild() {

//        OrderDtoMockedBuilderAndFactory orderDtoMockedBuilderAndFactory = OrderDtoMockedBuilderAndFactory.builder()
//                .status("OPEN")
//                .comment("comment")
//                .courierId(1)
//                .customerName(RandomDataGenerator.generateName())
//                .customerPhone("123456")
//                .comment("test")
//                .id(1)
//                .build();
        //Factory

        OrderDtoMockedBuilderAndFactory orderDtoMockedBuilderAndFactory = OrderDtoMockedBuilderAndFactory.createRandomOrder();


        given().
                header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .body(new Gson().toJson(orderDtoMockedBuilderAndFactory))
                .post("/test-orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);

    }
//    // Random data generation with Faker
//    @ParameterizedTest
//    @ValueSource(ints = {1,5,9,10})
//
//    public void updateOrderAndCheckResponseCodeIsOkWithFaker(int orderId) {
//        Faker faker = new Faker();
//        OrderDtoMocked orderDtoMocked = new OrderDtoMocked();
//
//        orderDtoMocked.setStatus("OPEN");
//        orderDtoMocked.setComment("comment");
//        orderDtoMocked.setCourierId(faker.number().numberBetween(1, 1000));
//        orderDtoMocked.setCustomerName(faker.name().fullName());
//        orderDtoMocked.setCustomerPhone(faker.phoneNumber().cellPhone()); // generated random phone number that contains only numerics
//        orderDtoMocked.setComment(faker.lorem().sentence()); // generated random alphanumeric comment which length 50 characters
//        orderDtoMocked.setId(faker.number().numberBetween(1, 1000));
//
//
//        RestAssured.given()
//                .baseUri("http://35.208.34.242:8080")
//                .header("accept", "application/json")
//                .header("api_key", "1234567890123456")
//                .contentType(ContentType.JSON)
//                .body(new Gson().toJson(orderDtoMocked))
//                //.body(new Gson().toJson(orderDtoMocked))
//                .put("/test-orders/5")
//                //.put("/test-orders/{id}", orderDtoMocked.getId())  // no need to indicate twice id
//                //.put()
//                .then()
//                .log()
//                .all()
//                .statusCode(HttpStatus.SC_OK);
//    }



    // response body
    @Test

    public void createOrderAndCheckResponseBody(){

        OrderDtoMockedBuilderAndFactory orderDtoMockedRequest = OrderDtoMockedBuilderAndFactory.builder()
                .status("OPEN")
                .comment("comment")
                .courierId(1)
                .customerName(RandomDataGenerator.generateName())
                .customerPhone("123456")
                .comment("test")
                .id(1)
                .build();

        //OrderDtoMockedBuilderAndFactory orderDtoMockedBuilderAndFactory = OrderDtoMockedBuilderAndFactory.createRandomOrder();

        Gson gson = new Gson();

        Response response = given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(new Gson().toJson(orderDtoMockedRequest))
                .post("/test-orders")
                .then()
                .extract()
                .response();
        //deserialization
        OrderDtoMocked orderReceived = gson.fromJson(response.asString(), OrderDtoMocked.class);
        //asserts
        Assertions.assertEquals("OPEN", orderReceived.getStatus());
        Assertions.assertEquals(orderDtoMockedRequest.getCustomerName(), orderReceived.getCustomerName());
    }



}
