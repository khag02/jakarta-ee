package org.tarot.tarothealing.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.tarot.tarothealing.util.DatabaseHealthChecker;

@Path("/health")
public class HealthResource {

    @Inject
    private DatabaseHealthChecker dbHealthCheck;

    @GET
    @Produces("text/plain")
    public Response health() {
        boolean status = dbHealthCheck.check();
        return status
                ? Response.ok("DB connection OK ✅").build()
                : Response.serverError().entity("DB connection FAILED ❌").build();
    }
}
