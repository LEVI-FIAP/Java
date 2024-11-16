package br.com.gslevi.resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/")
public class HomeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response home() {
        // Create the response map
        Map<String, Object> response = new HashMap<>();

        // Explicitly define and add the "owners" list
        response.put("owners", new ArrayList<>(Arrays.asList("fFukurou", "Erick Alves", "Luiz Neri")));

        // Add the project name
        response.put("project_name", "LEVI");

        // Explicitly define and add the "endpoints" list
        response.put("endpoints", new ArrayList<>(Arrays.asList("/users", "/users/{id}", "/regions", "/regions/{id}", "/reports", "/reports/{id}")));

        // Return the response as JSON
        return Response.ok(response).build();
    }

}
