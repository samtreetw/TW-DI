package com.garmin.di.impl;

import com.garmin.di.EchoService;
import com.garmin.di.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2016/3/31
 * Time: 15:43
 */
@Path("/echo-service")
@Service
public class EchoServiceImpl implements EchoService {

    @Autowired
    private Dao dao;

    @Override
    public String echo(String text) {
        return text;
    }

    @Override
    public String getAll() {
        List<String> all = dao.getAll();
        return all.isEmpty() ? "" : Arrays.toString(all.toArray());
    }

    @Override
    public Response redirect() {
        Response response = Response.seeOther(URI.create("http://localhost:8080/ciqarena/api/echo-service/echo?text=aaaa")).build();
        return response;
    }

    @Override
    public Response redirectPost() {
        Response response = Response.seeOther(URI.create("http://localhost:8080/ciqarena/api/echo-service/echo?text=POST")).build();
        return response;
    }

    @Override
    public Response customizeHeader() {
        Response.ResponseBuilder ok = Response.ok();
        ok.header("Test", "test");
        ok.header("Location", "aaaaaaaa");
        return ok.build();
    }
}
