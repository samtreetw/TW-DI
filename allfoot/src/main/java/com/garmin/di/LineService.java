package com.garmin.di;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: HuangLeo
 * Date: 2017/8/9
 * Time: 11:39
 */
public interface LineService {
    @POST
    @Path("/line")
    Response callback();
}
