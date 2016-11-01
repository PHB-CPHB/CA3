/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facades.UserFacade;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import security.IUserFacade;
import security.UserFacadeFactory;
import utils.makeTestUsers;

/**
 * REST Web Service
 *
 * @author plaul1
 */
@Path("demoall")
public class All {

    IUserFacade facade = UserFacadeFactory.getInstance();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of A
     */
    public All() {
    }

    /**
     * Retrieves representation of an instance of rest.All
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText() {
        System.out.println("XXXXXXXX---> " + System.getProperty("java.version"));
        return " {\"message\" : \"result for all\"}";
    }
    
    @Path("/create")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void createDB() {
        makeTestUsers.createDatabase();
    }

    @Path("/user")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createUser(String jsonString) {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        facade.addUser(json.get("username").getAsString(), json.get("password").getAsString());

    }

}
