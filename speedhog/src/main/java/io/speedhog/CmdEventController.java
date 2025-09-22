package io.speedhog;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;

@Path("/event")
public class CmdEventController {

    @Path("speedhog/{id}")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String recordEvent(
        @RestPath String type,
        @RestForm String eventBody
    ) {
        return "Speedhog event persisted; here is the eventBody: " + eventBody;
    }
}
