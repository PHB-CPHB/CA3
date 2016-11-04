/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import javax.ws.rs.core.Response;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import security.PasswordStorage;

/**
 *
 * @author philliphbrink
 */
public class AdminIntegrationTest {
    
    public AdminIntegrationTest() {
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
        given().
        when().get("/api/admin/users").
        then().statusCode(200).
        body("passwordHash[0]", equalTo("sha1:64000:18:WtnyHd6Sdz9Yl2W7T8PevKri5eFwFLlB:F+mIxZmuqyjgshbgpOJcS8Fc"), "userName[0]", equalTo("admin"), "roles[0].roleName[0]", equalTo("Admin"));
    }

//    /**
//     * Test of deleteUser method, of class Admin.
//     */
//    @Test
//    public void testDeleteUser() {
//        
//    }
    
}
