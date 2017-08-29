package com.garmin.di.test;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface TestService {
    
    @POST
    @Path("/test/{esn}/pass/{room_id}")
    @Produces(MediaType.APPLICATION_JSON)
    boolean passRoom(@PathParam("esn") String esn, @PathParam("room_id") Integer roomId);
    
}
