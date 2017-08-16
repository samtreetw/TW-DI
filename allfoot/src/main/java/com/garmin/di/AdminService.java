package com.garmin.di;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/15
 * Time: 09:55
 */
public interface AdminService {

    @PUT
    @Path("/resetDB")
    void resetDb();

    @PUT
    @Path("/resetGame")
    void resetGame();

    @PUT
    @Path("/game/status/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    boolean setGameStatus(@PathParam("status") String status);

    boolean addRoomRecord(String esn, int roomId);

}
