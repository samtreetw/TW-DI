package com.garmin.di.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: HuangLeo
 * Date: 2017/8/9
 * Time: 17:03
 */

public class LineBotProperties {

    private static Properties properties;
    private static String channelToken;
    private static String channelSecret;
    static {
        properties = new Properties();
        String configFile = LineBotProperties.class.getClassLoader().getResource("line-bot.properties").getPath();
        try {
            properties.load(new FileInputStream(configFile));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        channelToken = properties.getProperty("line.bot.channelToken", "token");
        channelSecret = properties.getProperty("line.bot.channelSecret", "secret");
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