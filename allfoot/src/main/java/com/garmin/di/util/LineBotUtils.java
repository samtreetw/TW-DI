package com.garmin.di.util;

import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.Multicast;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.message.*;
import com.linecorp.bot.model.message.imagemap.ImagemapAction;
import com.linecorp.bot.model.message.imagemap.ImagemapBaseSize;
import com.linecorp.bot.model.message.template.Template;
import com.linecorp.bot.model.response.BotApiResponse;
import com.sun.istack.NotNull;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: HuangLeo
 * Date: 2017/8/14
 * Time: 09:17
 * Refs: https://devdocs.line.me/en/#messaging-api
 */

public class LineBotUtils {

    /*
     * Message Generators
     */

    /**
     * Generate {@link TextMessage} for line-bot usage.
     *
     * @param text
     * @return {@link TextMessage}
     */
    public static TextMessage genTextMessage(String text) {
        return new TextMessage(text);
    }

    /**
     * Generate {@link ImageMessage} for line-bot usage.
     *
     * @param originalContentUrl
     * @param previewImageUrl
     * @return {@link ImageMessage}
     */
    public static ImageMessage genImageMessage(String originalContentUrl, String previewImageUrl) {
        return new ImageMessage(originalContentUrl, previewImageUrl);
    }

    /**
     * Generate {@link StickerMessage} for line-bot usage.
     * Available Stickers https://devdocs.line.me/files/sticker_list.pdf
     * @param packageId
     * @param stickerId
     * @return {@link StickerMessage}
     */
    public static StickerMessage genStickerMessage(@NotNull String packageId, @NotNull String stickerId) {
        return new StickerMessage(packageId, stickerId);
    }

    /**
     * Generate {@link LocationMessage} for line-bot usage.
     *
     * @param title
     * @param address
     * @param latitude
     * @param longitude
     * @return {@link LocationMessage}
     */
    public static LocationMessage genLocationMessage(String title, String address, double latitude, double longitude) {
        return new LocationMessage(title, address, latitude, longitude);
    }

    /**
     * Generate {@link AudioMessage} for line-bot usage.
     *
     * @param originalContentUrl
     * @param duration
     * @return {@link AudioMessage}
     */
    public static AudioMessage genAudioMessage(String originalContentUrl, Integer duration) {
        return new AudioMessage(originalContentUrl, duration);
    }

    /**
     * Generate {@link VideoMessage} for line-bot usage.
     *
     * @param originalContentUrl
     * @param previewImageUrl
     * @return {@link VideoMessage}
     */
    public static VideoMessage genVideoMessage(String originalContentUrl, String previewImageUrl) {
        return new VideoMessage(originalContentUrl, previewImageUrl);
    }

    /**
     * Generate {@link ImagemapMessage} for line-bot usage.
     *
     * @param baseUrl
     * @param altText
     * @param baseSize {@link ImagemapBaseSize}
     * @param actions {@link List<ImagemapAction>}
     * @return {@link ImagemapMessage}
     */
    public static ImagemapMessage genImagemapMessage(String baseUrl, String altText, ImagemapBaseSize baseSize, List<ImagemapAction> actions) {
        return new ImagemapMessage(baseUrl, altText, baseSize, actions);
    }

    /**
     * Generate {@link TemplateMessage} for line-bot usage.
     *
     * @param altText
     * @param template {@link Template}
     * @return {@link TemplateMessage}
     */
    public static TemplateMessage genTemplateMessage(String altText, Template template) {
        return new TemplateMessage(altText, template);
    }


    /*
     * Send Utilities
     */

    /**
     * Send {@link ReplyMessage} to reply a user's MessageEvent.
     *
     * @param event {@link MessageEvent} Incoming message from a specific user.
     * @param message {@link Message} Message to be sent for this reply message.
     */
    public static void sendReplyMessage(MessageEvent event, Message message) {
        ReplyMessage replyMessage = new ReplyMessage(event.getReplyToken(), message);
        Response<BotApiResponse> response;
        try {
            response = LineMessagingServiceBuilder
                    .create(LineBotProperties.getChannelToken())
                    .build()
                    .replyMessage(replyMessage)
                    .execute();
            System.out.println(response.code() + " " + response.message());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a {@link Message} to a user with {@link PushMessage}.
     *
     * @param to {@link String} User ID for sending this multicast.
     * @param message {@link Message} Message to be sent for this push message.
     */
    public static void sendPushMessage(String to, Message message) {
        PushMessage pushMessage = new PushMessage(to, message);
        Response<BotApiResponse> response;
        try {
            response =
            LineMessagingServiceBuilder
                    .create(LineBotProperties.getChannelToken())
                    .build()
                    .pushMessage(pushMessage)
                    .execute();
            System.out.println(response.code() + " " + response.message());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a {@link Message} to a set of users with {@link Multicast}.
     *
     * @param to {@link Set<String>} User IDs for sending this multicast.
     * @param message {@link Message} Message to be sent for this multicast.
     */
    public static void sendMulticast(Set<String> to, Message message) {
        Multicast multicast = new Multicast(to, message);
        Response<BotApiResponse> response;
        try {
            response =
                    LineMessagingServiceBuilder
                            .create(LineBotProperties.getChannelToken())
                            .build()
                            .multicast(multicast)
                            .execute();
            System.out.println(response.code() + " " + response.message());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
