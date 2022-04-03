package org.acme.rest.client;

import org.jboss.resteasy.plugins.providers.FormUrlEncodedProvider;
import org.jboss.resteasy.plugins.providers.JaxrsFormProvider;
import org.jboss.resteasy.plugins.providers.ServerFormUrlEncodedProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/request")
public class RequestResource {

    @GET
    @Path("self")
    @Produces(MediaType.APPLICATION_JSON)
    public String sendSelfRequest() {
        return getFormResponse("http://localhost:8082/targetForm", "Hello", "World!");
    }

    @GET
    @Path("remote")
    @Produces(MediaType.APPLICATION_JSON)
    public String sendRemoteRequest() {
        return getFormResponse("http://localhost:8081/targetForm", "Hello", "World!");
    }

    private String getFormResponse(final String targetEndpoint, final String firstName, final String lastName) {
        final var client = ClientBuilder.newClient();
        // Uncommenting the line below also fix the issue.
        //  client.register(JaxrsFormProvider.class);
        // These two providers are also automatically registered when @Consumes(MediaType.APPLICATION_FORM_URLENCODED) annotation is present.
        // - FormUrlEncodedProvider.class
        // - ServerFormUrlEncodedProvider.class

        final var target = client.target(targetEndpoint);

        Response response = target
                .request()
                .header("cache-control", "no-cache")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.form(buildForm(firstName, lastName)));
        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        }
        throw new RuntimeException("Error when requesting");
    }

    private Form buildForm(final String firstName, final String lastName) {
        Form form = new Form();
        form.param("firstName", firstName);
        form.param("lastName", lastName);
        return form;
    }
}