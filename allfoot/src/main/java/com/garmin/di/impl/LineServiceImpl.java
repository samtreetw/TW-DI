package com.garmin.di.impl;

import com.garmin.di.dao.DbBase;
import com.garmin.di.dao.GameDao;
import com.garmin.di.dto.EventContent;
import com.garmin.di.util.LineBotProperties;
import com.garmin.di.LineService;
import com.garmin.di.util.LineBotUtils;
import com.linecorp.bot.client.LineSignatureValidator;
import com.linecorp.bot.model.event.*;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.message.*;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.servlet.LineBotCallbackRequestParser;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private static Logger logger = LoggerFactory.getLogger(LineBotUtils.class);

    private DbBase dbBase;
    private GameDao gameDao;

    @Autowired
    public LineServiceImpl(DbBase dbBase, GameDao gameDao) {
        this.dbBase = dbBase;
        this.gameDao = gameDao;
    }

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
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        // Always return 200 OK to line message request
        return javax.ws.rs.core.Response.ok().build();
    }

    /*
     * Event Handlers
     */
    private void handleMessageEvent(MessageEvent event) throws Exception {
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

    private void handlePostbackEvent(PostbackEvent event) throws Exception {

        // Postback content data should be like "name1:value1,name2:value2 ..."
        ArrayList<Pair<String, String>> arrayList = new ArrayList<>();
        String[] pairs = event.getPostbackContent().getData().split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            arrayList.add(new ImmutablePair<>(keyValue[0], keyValue[1]));
        }

        String lineId = event.getSource().getUserId();
        for (Pair<String, String> item : arrayList) {
            switch (item.getKey()) {
                case "UserLineID":
                    if (gameDao.updatePlayerLineId(item.getValue(), lineId)) {
                        LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Player " + item.getValue() + "'s Line ID is updated."));
                    } else {
                        LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Fail to update player " + item.getValue() + "'sLine ID."));
                    }
                    break;
                default:
                    Integer answer = gameDao.getAnswer(Integer.valueOf(item.getKey()));
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage(Integer.valueOf(item.getValue()) == answer ? "Correct" : "Wrong"));
                    break;
            }
        }

    }

    private void handleBeaconEvent(BeaconEvent event) {
    }


    /*
     * Message Handlers
     */
    private void handleTextMessage(final MessageEvent event) throws Exception {
        String originText = ((TextMessageContent) event.getMessage()).getText();
        final String question;
        final ArrayList<Pair<String, String>> answers ;
        switch (originText) {
            case "add user":
                question = "Who are you?";
                answers = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    answers.add(new ImmutablePair<>("User " + String.valueOf(i+1), String.valueOf(i+1)));
                }
                LineBotUtils.sendReplyMessage(event, LineBotUtils.genQuestion("Here comes a question.", "UserLineID", question, answers.subList(0, 4)));
                LineBotUtils.sendPushMessage(event.getSource().getUserId(), LineBotUtils.genQuestion("Here comes a question.", "UserLineID", question, answers.subList(4, 8)));
                break;
            case "add admin":
                UserProfileResponse userProfileResponse = LineBotUtils.getUserProfile(event.getSource().getUserId());
                if (userProfileResponse == null) {
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Unable to retrieve profile. Fail to add you as admin."));
                    return;
                }
                if (gameDao.addAdmin(userProfileResponse.getDisplayName(), userProfileResponse.getUserId())) {
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage(userProfileResponse.getDisplayName() + " has been added as admin."));
                } else {
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Fail to add you as admin. You may be an existing admin."));
                }
                break;
            case LineBotUtils.ANSWER_SENT:
                // Receive answer sent string, do nothing
                break;
            case "test":
                // Push message test
                EventContent eventContent = gameDao.getRoom(1).getRoomEvent().getEventContent();
                question = eventContent.getEvent();
                answers = new ArrayList<>();
                for (int i = 0; i < eventContent.getEventOptions().size(); i++) {
                    answers.add(new ImmutablePair<>(eventContent.getEventOptions().get(i), String.valueOf(i+1)));
                }
                Timer timer = new Timer(10000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LineBotUtils.sendPushMessage(event.getSource().getUserId(), LineBotUtils.genStickerMessage("2", "141"));
                        LineBotUtils.sendPushMessage(event.getSource().getUserId(), LineBotUtils.genTextMessage("This is push message."));
                        LineBotUtils.sendPushMessage(event.getSource().getUserId(), LineBotUtils.genQuestion("Here comes a question.", "1", question, answers));
                    }
                });
                timer.setRepeats(false);
                timer.start();
                break;

            // Admin Only Commands
            case "reset db":
                if (gameDao.isAdmin(event.getSource().getUserId())) {
                    dbBase.drop();
                    dbBase.setup();
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Reset DB success."));
                } else {
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Reset DB fail."));
                }
                break;
            case "reset game":
                if (gameDao.isAdmin(event.getSource().getUserId())) {
                    dbBase.reset();
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Reset game success."));
                } else {
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Reset game fail."));
                }
                break;

            default:
                LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage(originText));
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
