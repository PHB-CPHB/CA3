/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import scheduler.getExchangeRate;

/**
 * REST Web Service
 *
 * @author kaspe
 */
@Path("currency")

public class Currency {
    
    getExchangeRate getRate = new getExchangeRate();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Currency
     */
    public Currency() {
    }

    /**
     * Retrieves representation of an instance of rest.Currency
     *
     * @return an instance of java.lang.String
     */
    @Path("/dailyrates")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getDailyRates() {
        Gson gson = new Gson();
        return gson.toJson(getRate.getRate());
    }

    /**
     * PUT method for updating or creating an instance of Currency
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
