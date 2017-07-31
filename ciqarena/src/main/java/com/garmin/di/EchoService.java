package com.garmin.di;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2016/3/31
 * Time: 15:43
 */
public interface EchoService {

    @GET
    @Path("/echo")
    @Produces(MediaType.APPLICATION_JSON)
    String echo(@QueryParam("text") String text);

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    String getAll();

    @GET
    @Path("/redirect")
    Response redirect();

    @POST
    @Path("/redirectpost")
    Response redirectPost();

    @GET
    @Path("header")
    Response customizeHeader();
}
