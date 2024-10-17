package org.mostafa;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mostafa.stubs.InventoryStubs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;

import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderApplicationTests {
    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

//    @Test
//    void shouldSubmitOrder() {
//        String submitOrderJson = """
//                {
//                     "skuCode": "iphone_15",
//                     "price": 1000,
//                     "quantity": 1
//                }
//                """;
//
//        var responseBodyString = RestAssured.given()
//                .contentType("application/json")
//                .body(submitOrderJson)
//                .when()
//                .post("/api/order")
//                .then()
//                .log().all()
//                .statusCode(201)
//                .extract()
//                .body().asString();
//
//        assertThat(responseBodyString, Matchers.is("Order Placed Successfully"));
//    }

    @Test
    void shouldSubmitOrderUsingWireMock() {
        String submitOrderJson = """
                {
                     "skuCode": "iphone_15",
                     "price": 1000,
                     "quantity": 1
                }
                """;

        InventoryStubs.stubInventoryCall("iphone_15", 1);
        var responseBodyString = RestAssured.given()
                .contentType("application/json")
                .body(submitOrderJson)
                .when()
                .post("/api/order")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .body().asString();

        assertThat(responseBodyString, Matchers.is("Order Placed Successfully"));
    }

}
