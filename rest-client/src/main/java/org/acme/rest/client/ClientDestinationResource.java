package org.acme.rest.client;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/targetForm")
public class ClientDestinationResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    /* If Consumes annotation is uncommented, client works normally because the three providers below are automatically registered
        (although only the first one is necessary for the client to work):
        - JaxrsFormProvider
        - FormUrlEncodedProvider
        - ServerFormUrlEncodedProvider
    */
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String hello(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        return "Hello '" + firstName + " " + lastName + "'";
    }
}