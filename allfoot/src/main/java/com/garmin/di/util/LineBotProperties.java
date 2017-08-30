package com.garmin.di.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: HuangLeo
 * Date: 2017/8/9
 * Time: 17:03
 */

public class LineBotProperties {

    private static Logger logger = LoggerFactory.getLogger(LineBotProperties.class);

    private static String channelToken;
    private static String channelSecret;

    static {
        channelToken = System.getProperty("line.bot.channelToken", "token");
        channelSecret = System.getProperty("line.bot.channelSecret", "secret");

        logger.info("Token:" + channelToken);
        logger.info("Secret:" + channelSecret);
    }

    //getters and setters
    public static String getChannelToken() {
        return channelToken;
    }

    public static void setChannelToken(String token) {
        channelToken = token;
    }

    public static String getChannelSecret() {
        return channelSecret;
    }

    public static void setChannelSecret(String secret) {
        channelSecret = secret;
    }
}
