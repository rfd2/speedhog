package io.speedhog;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/event")
public class QueryEventController {

    @Path("speedhog/{id}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String recordEvent() {
        return "Speedhog event data retrieved.";
    }
}
