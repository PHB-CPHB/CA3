/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import security.IUserFacade;
import security.UserFacadeFactory;

@Path("/admin")
@RolesAllowed("Admin")
public class Admin {
    IUserFacade facade = UserFacadeFactory.getInstance();
    Gson gson = new Gson();
    
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){
        return Response.ok(gson.toJson(facade.getAllUsers())).build();
    }
}
