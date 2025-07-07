package org.tarot.tarothealing.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.logging.Logger;

@Path("/hello-world")
public class HelloResource {
    Logger logger = Logger.getLogger(HelloResource.class.getName());

    @GET
    @Produces("text/plain")
    public String hello() {
        logger.info("Hello, Khang!");
        return "Hello, Khang!";
    }
}
