package com.example.offline.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/heartbeat")
@Produces({MediaType.TEXT_HTML})
public class HeartbeatResource {
    @GET
    public Response getHeartbeat() {
        return Response.ok().build();
    }
}
