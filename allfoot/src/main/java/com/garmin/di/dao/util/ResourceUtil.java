package com.garmin.di.dao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: TsaiSamuel
 * Date: 2015/1/12
 * Time: 18:34
 */
public class ResourceUtil {

    static Logger logger = LoggerFactory.getLogger(ResourceUtil.class);

    /**
     * Reads the contents of the provided file Resource and returns it as a String.  Assertions
     * are made that the Resource is not null, and that the contents aren't empty.
     *
     * @param resource
     * @return
     */
    public static String readFileContents(Resource resource) {
        return readFileContents(resource, true);
    }

    /**
     * Reads the contents of the provided file Resource and returns it as a String.  Assertions
     * are made that the Resource is not null, and that the contents aren't empty.
     *
     * @param resource
     * @param endWithLineBreaker
     * @return
     */
    public static String readFileContents(Resource resource, boolean endWithLineBreaker) {
        if (logger.isDebugEnabled()) {
            logger.debug("Reading File Contents for: " + resource);
        }

        Assert.notNull(resource, "readFileContents cannot be called with a null resource.");

        String result = "";
        BufferedReader bufferedReader = null;

        try {
            StringBuilder sb = new StringBuilder();

            InputStreamReader in = new InputStreamReader(resource.getInputStream(), Charset.forName("UTF-8"));
            bufferedReader = new BufferedReader(in);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            /* Remove the line breaker */
            if (!endWithLineBreaker && sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            result = sb.toString();
        } catch (Exception e) {
            logger.error("An error occurred reading file contents.", e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException err) {
                    logger.error("Failed to close " + resource.getFilename());
                }
            }
        }

        Assert.hasLength(result, resource.getFilename() + " cannot be empty");

        return result;

    }
}
