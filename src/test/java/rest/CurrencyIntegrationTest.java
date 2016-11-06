/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.given;

/**
 *
 * @author philliphbrink
 */
public class CurrencyIntegrationTest {
    
    public CurrencyIntegrationTest() {
    }
    
    private static String securityToken;
    
    private static void login(String role, String password) {
    String json = String.format("{username: \"%s\", password: \"%s\"}",role,password);
    System.out.println(json);
    securityToken = given()
            .contentType("application/json")
            .body(json)
            .when().post("/api/login")
            .then()
            .extract().path("token");
    System.out.println("Token: " + securityToken);

  }
    
    @BeforeClass
    public static void setUpBeforeAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/seedMaven";
        RestAssured.defaultParser = Parser.JSON;
    }

    /**
     * Test of getDailyRates method, of class Currency.
     */
    @Test
    public void testGetDailyRates() {
        login("user","test");
        given()
        .contentType("application/json")
        .header("Authorization", "Bearer " + securityToken)
        .when()
        .get("/api/currency/dailyrates")
        .then()
        .statusCode(200)
        .body("dailyRates", equalTo("2016-11-04"), "currency[0].code", equalTo("SGD"));
    }
    
    /**
     * Test of getexchangeCalc method, of class Currency.
     */
    @Test
    public void testexchangeCalc() {
        login("user","test");
        given()
        .contentType("application/json")
        .header("Authorization", "Bearer " + securityToken)
        .pathParam("amount", "100")
        .pathParam("fromCurrency", "USD")
        .pathParam("toCurrency", "NOK")        
        .when()
        .get("/api/currency/calculator/{amount}/{fromCurrency}/{toCurrency}")
        .then()
        .statusCode(200)
        .body("value", equalTo("818.63666"));
    }
    
}
