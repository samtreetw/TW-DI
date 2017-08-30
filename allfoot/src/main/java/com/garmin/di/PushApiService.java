package com.garmin.di;

import com.sun.jersey.multipart.MultiPart;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/30
 * Time: 11:45
 */
public interface PushApiService {

    @POST
    @Path("/gc")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    Response uploadFromGc(@Context HttpHeaders headers, MultiPart multiPart);
}
