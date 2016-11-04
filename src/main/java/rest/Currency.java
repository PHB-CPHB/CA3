/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import calculator.Calc;
import com.google.gson.Gson;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import scheduler.getExchangeRate;
import security.IUserFacade;
import security.UserFacadeFactory;

/**
 * REST Web Service
 *
 * @author kaspe
 */
@Path("currency")
@RolesAllowed("User")
public class Currency {

    getExchangeRate getRate = new getExchangeRate();
    Calc calculator = new Calc();
    IUserFacade facade = new UserFacadeFactory().getInstance();

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
    @Produces(MediaType.APPLICATION_JSON)
    public String getDailyRates() {
        Gson gson = new Gson();
        return gson.toJson(getRate.getRate());
    }

    @Path("/calculator/{amount}/{fromCurrency}/{toCurrency}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String exchangeCalc(@PathParam("amount") String amount, @PathParam("fromCurrency") String fCurrency, @PathParam("toCurrency") String tCurrency) {
        Gson gson = new Gson();
        calculator.calcRate(facade.getRateByCode(fCurrency), facade.getRateByCode(tCurrency), amount);
        return gson.toJson(calculator);
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
