package org.acme.rest.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/targetForm")
public class DestinationResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String hello(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        return "Hello '" + firstName + " " + lastName + "'";
    }
}