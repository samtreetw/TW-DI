package com.garmin.di.impl;

import com.garmin.di.dao.DbBase;
import com.garmin.di.dao.GameDao;
import com.garmin.di.dao.PlayerDao;
import com.garmin.di.dto.ActionContent;
import com.garmin.di.dto.enums.GameStatus;
import com.garmin.di.util.LineBotProperties;
import com.garmin.di.LineService;
import com.garmin.di.util.LineBotUtils;
import com.linecorp.bot.client.LineSignatureValidator;
import com.linecorp.bot.model.event.*;
import com.linecorp.bot.model.event.message.*;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.servlet.LineBotCallbackRequestParser;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: HuangLeo
 * Date: 2017/8/9
 * Time: 15:58
 */
@Path("/line-service")
@Service
public class LineServiceImpl implements LineService {
    private static final int MAX_TRIAL = 3;
    @Context
    private HttpServletRequest httpServletRequest;
    private static LineBotCallbackRequestParser lineBotCallbackRequestParser = new LineBotCallbackRequestParser(new LineSignatureValidator(LineBotProperties.getChannelSecret().getBytes()));
    private static Logger logger = LoggerFactory.getLogger(LineBotUtils.class);

    private DbBase dbBase;
    private GameDao gameDao;
    private PlayerDao playerDao;

    @Autowired
    public LineServiceImpl(DbBase dbBase, GameDao gameDao, PlayerDao playerDao) {
        this.dbBase = dbBase;
        this.gameDao = gameDao;
        this.playerDao = playerDao;
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
            String key = item.getKey();
            com.garmin.di.dto.enums.ActionEvent actionEvent = com.garmin.di.dto.enums.ActionEvent.getByName(key);
            if (actionEvent != null) {
                handleActionEvent(actionEvent, lineId, item.getValue(), key);
            } else {
                switch (key) {
                    case "UserLineID":
                        if (playerDao.updatePlayerLineId(item.getValue(), lineId)) {
                            LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Player " + item.getValue() + "'s Line ID is updated."));
                        } else {
                            LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Fail to update player " + item.getValue() + "'sLine ID."));
                        }
                        break;
                    case "PassRoom":
                        int room = playerDao.getPlayerLocation(item.getValue());
                        if (gameDao.passRoom(item.getValue(), room) && gameDao.unLockPlayer(item.getValue())) {
                            LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Player " + item.getValue() + " has passed room " + room + "."));
                        } else {
                            LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Pass Room Fail."));
                        }
                        break;
                    default:
                        Integer answer = gameDao.getAnswer(item.getKey());
                        String esn = playerDao.getPlayerEsnByLineId(lineId);
                        int roomId = gameDao.getRoomIdByEventId(key);
                        int trial = gameDao.getRoomTrialCount(roomId, esn);
                        if (gameDao.getGameRank(esn, roomId) != -1) {
                            LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("You have already finished this question!"));
                            return;
                        }
                        if (trial > MAX_TRIAL){
                            LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("You have already finished this question!"));
                            return;
                        }
                        if (Objects.equals(Integer.valueOf(item.getValue()), answer)) {
                            LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Correct!"));
                            gameDao.passRoom(esn, roomId);
                            gameDao.unLockPlayer(esn);
                        } else {
                            gameDao.insertRoomTrial(roomId, esn);
                            LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Wrong!"));
                            if ((trial+1) == MAX_TRIAL) {
                                gameDao.insertRoomTrial(roomId, esn);
                                gameDao.unLockPlayer(esn);
                                LineBotUtils.sendPushMessage(lineId, LineBotUtils.genTextMessage("No more chances for this question!\nYou could go to another room now."));
                                return;
                            }
                        }
                        break;
                }
            }
        }

    }

    final private static int SCORE_TO_STEAL = 3;

    private void handleActionEvent(com.garmin.di.dto.enums.ActionEvent actionEvent, String lineId, String esn, String questionId) {
        String originEsn = playerDao.getPlayerEsnByLineId(lineId);
        int roomId = gameDao.getRoomIdByEventId(questionId);
        if (gameDao.getGameRank(originEsn, roomId) != -1) {
            LineBotUtils.sendPushMessage(lineId, LineBotUtils.genTextMessage("You have already finished this!"));
            return;
        }
        switch (actionEvent) {
            case CHANGE_SCORE: {
                playerDao.switchPlayersScores(lineId, esn);
                TextMessage textMessage = LineBotUtils.genTextMessage("Exchange score done!");
                LineBotUtils.sendPushMessage(lineId, textMessage);
                break;
            }
            case STOLE_SCORE: {
                playerDao.stealPlayerScore(lineId, esn, SCORE_TO_STEAL);
                TextMessage textMessage = LineBotUtils.genTextMessage("Steal score done!");
                LineBotUtils.sendPushMessage(lineId, textMessage);
                break;
            }
            default:
                return;
        }

        ActionContent actionContent = gameDao.getAction(actionEvent.getName());
        TextMessage textMessage = LineBotUtils.genTextMessage(actionContent.getNotificationTextB());
        LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), textMessage);
        gameDao.addGameRecord(originEsn, roomId);
        gameDao.unLockPlayer(playerDao.getPlayerEsnByLineId(lineId));
    }

    private void handleBeaconEvent(BeaconEvent event) {
    }


    /*
     * Message Handlers
     */
    private void handleTextMessage(final MessageEvent event) throws Exception {
        String originText = ((TextMessageContent) event.getMessage()).getText().trim().toLowerCase();
        final String question;
        final ArrayList<Pair<String, String>> answers;
        switch (originText) {
            case "add user":
                question = "Who are you?";
                LineBotUtils.sendPlayerChoiceOptions(event, question, "UserLineID");
                break;
            case LineBotUtils.ANSWER_SENT:
                // Receive answer sent string, do nothing
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
            case "pass":
                if (gameDao.isAdmin(event.getSource().getUserId())) {
                    question = "Which team should pass the room?";
                    LineBotUtils.sendPlayerChoiceOptions(event, question, "PassRoom");
                } else {
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("You are not allow to do this."));
                }
                break;
            case "score":
                if (gameDao.isAdmin(event.getSource().getUserId())) {
                    StringBuilder sb = new StringBuilder();
                    for (String s : playerDao.getAllPlayerScores()) {
                        sb.append(s);
                        sb.append("\n");
                    }
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage(sb.toString()));
                } else {
                    String score = String.valueOf(playerDao.getPlayerScoreByLineId(event.getSource().getUserId()));
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage(score));
                }
                break;
            case "game":
                if (gameDao.isAdmin(event.getSource().getUserId())) {
                    String status = gameDao.getGameStatus().name();
                    LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage(status));
                }
                break;
            default:
                // Admin Only Commands
                if (gameDao.isAdmin(event.getSource().getUserId())) {
                    if (originText.matches("set game ([0-3])")) {
                        String[] group = originText.split(" ");
                        gameDao.updateGameStatus(GameStatus.lookup(Integer.valueOf(group[2])));
                        LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Game Status is updated."));
                        return;
                    } else if (originText.matches("lock ([1-8])")) {
                        String[] group = originText.split(" ");
                        gameDao.lockPlayer(group[1]);
                        LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Player " + group[1] + " is locked."));
                        return;
                    }  else if (originText.matches("unlock ([1-8])")) {
                        String[] group = originText.split(" ");
                        gameDao.unLockPlayer(group[1]);
                        LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Player " + group[1] + " is unlocked."));
                        return;
                    } else if (originText.matches("set score ([1-8]) ([0-9]*)")) {
                        String[] group = originText.split(" ");
                        // Update user score.
                        boolean updateScoreStatus = playerDao.updatePlayerScore(group[2], Integer.valueOf(group[3]));
                        if (updateScoreStatus) {
                            LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Player " + group[2] + "'s score is now " + group[3]));
                        } else {
                            LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Set score failed."));
                        }
                        return;
                    } else if (originText.matches("test ([1-8])")) {
                        String[] group = originText.split(" ");
                        LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(group[1]), LineBotUtils.genTextMessage("This is test."));
                        return;
                    }
                }
                LineBotUtils.sendReplyMessage(event, LineBotUtils.genTextMessage("Wrong command.\nPlease check again."));
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
