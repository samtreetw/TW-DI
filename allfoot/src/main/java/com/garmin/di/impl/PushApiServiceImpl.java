package com.garmin.di.impl;

import com.garmin.di.PushApiService;
import com.sun.jersey.multipart.MultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/30
 * Time: 11:45
 */
@Path("/push")
@Service
public class PushApiServiceImpl implements PushApiService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Response uploadFromGc(HttpHeaders headers, MultiPart multiPart) {
        Set<Map.Entry<String, List<String>>> entries = headers.getRequestHeaders().entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            for (String value : entry.getValue()) {
                logger.info(entry.getKey() + ":" + value);
            }
        }
        return null;
    }
}
