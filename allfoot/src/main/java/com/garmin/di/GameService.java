package com.garmin.di;

import com.garmin.di.dto.LinkedRoom;
import org.apache.commons.lang3.tuple.Pair;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2016/3/31
 * Time: 15:43
 */
public interface GameService {

    /**
     * No longer used
     * @param esn
     * @return
     */
    @POST
    @Path("/join/{esn}")
    @Produces(MediaType.APPLICATION_JSON)
    String join(@PathParam("esn") String esn);

    @GET
    @Path("/{esn}/linked")
    @Produces(MediaType.APPLICATION_JSON)
    List<LinkedRoom> getLinkedRoom(@PathParam("esn") String esn);

    @PUT
    @Path("/{esn}/goto/{room_id}")
    @Produces(MediaType.APPLICATION_JSON)
    boolean gotoRoom(@PathParam("esn") String esn, @PathParam("room_id") Integer roomId);

    @GET
    @Path("/{esn}/battle")
    @Produces(MediaType.APPLICATION_JSON)
    Pair<Integer, Integer> battleWithBoss(@PathParam("esn") String esn);
    
}
