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
import org.junit.BeforeClass;

/**
 *
 * @author philliphbrink
 */
public class AdminIntegrationTest {
    
    public AdminIntegrationTest() {
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
     * Test of getAllUsers method, of class Admin.
     */
    @Test
    public void testGetAllUsers() {
        login("admin","test");
        given()
        .contentType("application/json")
        .header("Authorization", "Bearer " + securityToken)
        .when()
        .get("/api/admin/users")
        .then()
        .statusCode(200)
        .body("userName[0]", equalTo("admin"),
              "roles[0].roleName[0]", equalTo("Admin"));
    }

//    /**
//     * Test of deleteUser method, of class Admin.
//     */
//    @Test
//    public void testDeleteUser() {
//        
//    }
    
}
