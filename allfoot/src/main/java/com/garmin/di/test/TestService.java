package com.garmin.di.test;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.garmin.di.dto.Room;

public interface TestService {
    
    @POST
    @Path("/test/{esn}/pass/{room_id}")
    @Produces(MediaType.APPLICATION_JSON)
    boolean passRoomTest(@PathParam("esn") String esn, @PathParam("room_id") Integer roomId);
    
    @PUT
    @Path("/test/{esn}/goto/{room_id}")
    @Produces(MediaType.APPLICATION_JSON)
    Room gotoRoomTest(@PathParam("esn") String esn, @PathParam("room_id") Integer roomId);
    
}
