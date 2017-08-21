package com.garmin.di.impl;

import com.garmin.di.util.LineBotProperties;
import com.garmin.di.LineService;
import com.garmin.di.util.LineBotUtils;
import com.linecorp.bot.client.LineSignatureValidator;
import com.linecorp.bot.model.event.*;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.message.*;
import com.linecorp.bot.servlet.LineBotCallbackException;
import com.linecorp.bot.servlet.LineBotCallbackRequestParser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: HuangLeo
 * Date: 2017/8/9
 * Time: 15:58
 */
@Path("/line-service")
@Service
public class LineServiceImpl implements LineService {
    @Context
    private HttpServletRequest httpServletRequest;
    private static LineBotCallbackRequestParser lineBotCallbackRequestParser = new LineBotCallbackRequestParser(new LineSignatureValidator(LineBotProperties.getChannelSecret().getBytes()));

    @Override
    public javax.ws.rs.core.Response callback() {
        try {
            CallbackRequest callbackRequest = lineBotCallbackRequestParser.handle(httpServletRequest);
            for (Event event : callbackRequest.getEvents()) {
                // Handle event based on event type.
                switch (event.getClass().getSimpleName()) {
                    case "MessageEvent":
                        handleMessageEvent((MessageEvent) event);
                        break;
                    case "UnfollowEvent":
                        handleUnfollowEvent((UnfollowEvent) event);
                        break;
                    case "FollowEvent":
                        handleFollowEvent((FollowEvent) event);
                        break;
                    case "JoinEvent":
                        handleJoinEvent((JoinEvent) event);
                        break;
                    case "LeaveEvent":
                        handleLeaveEvent((LeaveEvent) event);
                        break;
                    case "PostbackEvent":
                        handlePostbackEvent((PostbackEvent) event);
                        break;
                    case "BeaconEvent":
                        handleBeaconEvent((BeaconEvent) event);
                        break;
                    default:
                        break;
                }
            }
        } catch (LineBotCallbackException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Always return 200 OK to line message request
        return javax.ws.rs.core.Response.ok().build();
    }

    /*
     * Event Handlers
     */
    private void handleMessageEvent(MessageEvent event) {
        if (event.getMessage() instanceof TextMessageContent) {
            handleTextMessage(event);
        } else if (event.getMessage() instanceof ImageMessageContent) {
            handleImageMessage(event);
        } else if (event.getMessage() instanceof VideoMessageContent) {
            handleVideoMessage(event);
        } else if (event.getMessage() instanceof AudioMessageContent) {
            handleAudioMessage(event);
        } else if (event.getMessage() instanceof FileMessageContent) {
            handleFileMessage(event);
        } else if (event.getMessage() instanceof LocationMessageContent) {
            handleLocationMessage(event);
        } else if (event.getMessage() instanceof StickerMessageContent) {
            handleStickerMessage(event);
        } else {
            // Unrecognized message, do nothing
        }
    }

    private void handleUnfollowEvent(UnfollowEvent event) {
    }

    private void handleFollowEvent(FollowEvent event) {
    }

    private void handleJoinEvent(JoinEvent event) {
    }

    private void handleLeaveEvent(LeaveEvent event) {
    }

    private void handlePostbackEvent(PostbackEvent event) {
        // todo Check if the answer is correct
        LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage(event.getPostbackContent().getData()));
    }

    private void handleBeaconEvent(BeaconEvent event) {
    }


    /*
     * Message Handlers
     */
    private void handleTextMessage(final MessageEvent event) {
        String originText = ((TextMessageContent) event.getMessage()).getText();
        switch (originText) {
            case "save":
                // todo Save User ID
                break;
            case "save admin":
                // todo Save Admin ID
                break;
            case LineBotUtils.ANSWER_SENT:
                // Receive answer sent string, do nothing
                break;
            default:
                LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage(originText));

                // Push message
                final String testQuestion = "This is a test question.";
                final ArrayList<String> testAnswers = new ArrayList<>();
                testAnswers.add("Answer 1");
                testAnswers.add("Answer 2");
                testAnswers.add("Answer 3");
                testAnswers.add("Answer 4");
                Timer timer = new Timer(10000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LineBotUtils.sendPushMessage(event.getSource().getUserId(), LineBotUtils.genStickerMessage("2", "141"));
                        LineBotUtils.sendPushMessage(event.getSource().getUserId(), LineBotUtils.genTextMessage("This is push message."));
                        LineBotUtils.sendPushMessage(event.getSource().getUserId(), LineBotUtils.genQuestion("Here comes a question.", "1", testQuestion, testAnswers));
                    }
                });
                timer.setRepeats(false);
                timer.start();
                break;
        }
    }

    private void handleImageMessage(MessageEvent event) {

    }

    private void handleVideoMessage(MessageEvent event) {

    }

    private void handleAudioMessage(MessageEvent event) {

    }

    private void handleFileMessage(MessageEvent event) {

    }

    private void handleLocationMessage(MessageEvent event) {

    }

    private void handleStickerMessage(MessageEvent event) {

    }
}
