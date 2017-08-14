package com.garmin.di.impl;

import com.garmin.di.util.LineBotProperties;
import com.garmin.di.LineService;
import com.garmin.di.util.LineBotUtils;
import com.linecorp.bot.client.LineSignatureValidator;
import com.linecorp.bot.model.event.*;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.message.TextMessageContent;
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

    private void handleMessageEvent(MessageEvent event) {
        if (event.getMessage() instanceof TextMessageContent) {
            handleTextMessage(event);
        }
        return;
    }

    private void handleTextMessage(final MessageEvent event) {
        String originText = ((TextMessageContent) event.getMessage()).getText();
        LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage(originText));
        Timer timer = new Timer( 30000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ){
                LineBotUtils.sendPushMessage(event.getSource().getUserId(), LineBotUtils.genStickerMessage("2", "141"));
                LineBotUtils.sendPushMessage(event.getSource().getUserId(), LineBotUtils.genTextMessage("This is push message."));
            }
        } );
        timer.setRepeats( false );
        timer.start();
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
    }

    private void handleBeaconEvent(BeaconEvent event) {
    }
}
