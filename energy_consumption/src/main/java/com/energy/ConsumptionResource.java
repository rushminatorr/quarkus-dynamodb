package com.energy;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/consumption")
public class ConsumptionResource {

    @Inject
    EnergyService energyService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{date}")
    public String greeting(@PathParam("date") String date) {
        return energyService.dateUsage(date);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("customer/{customer}/{date}")
    public String user(@PathParam("customer") String customer,
                       @PathParam("date") String date) {
        return energyService.customerUsage(customer,date);
    }
}


