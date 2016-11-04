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
        given().
        when().get("/api/currency/dailyrates").
        then().statusCode(200).
        body("dailyRates", equalTo("2016-11-03"), "currency[0].code", equalTo("SGD"));
    }
    
    /**
     * Test of getexchangeCalc method, of class Currency.
     */
    @Test
    public void testexchangeCalc() {
        given()
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
