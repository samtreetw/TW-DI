package com.garmin.di.impl;

import com.garmin.di.GameService;
import com.garmin.di.dao.GameDao;
import com.garmin.di.dao.PlayerDao;
import com.garmin.di.dao.util.RoomWrapper;
import com.garmin.di.dto.ActionContent;
import com.garmin.di.dto.EventContent;
import com.garmin.di.dto.LinkedRoom;
import com.garmin.di.dto.Player;
import com.garmin.di.dto.Room;
import com.garmin.di.dto.RoomEvent;
import com.garmin.di.dto.enums.ActionEvent;
import com.garmin.di.dto.enums.EventType;
import com.garmin.di.dto.enums.GameStatus;
import com.garmin.di.dto.enums.PlayerStatus;
import com.garmin.di.util.LineBotUtils;
import com.linecorp.bot.model.message.Message;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2016/3/31
 * Time: 15:43
 */
@Path("/game-service")
@Service
public class GameServiceImpl implements GameService {

    final private static int INCREMENTAL_STEPS = 50;
    final private static int PHASE_ONE_STARTING_ROOM_ID = 0;
    final private static int PHASE_TWO_STARTING_ROOM_ID = 11;
    private GameDao gameDao;
    private PlayerDao playerDao;

    @Autowired
    public GameServiceImpl(GameDao gameDao, PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    @Override
    public String join(String esn) {
        return String.valueOf(gameDao.joinGame(esn));
    }

    @Override
    public List<LinkedRoom> getLinkedRoom(String esn) {
        Player player = playerDao.getPlayer(esn);
        if (player.getPlayerStatus() == PlayerStatus.LOCK || 
        		gameDao.getGameStatus() == GameStatus.PREPARE ||
        		gameDao.getGameStatus() == GameStatus.START) {
            return Collections.emptyList();
        } else {
            List<LinkedRoom> linkedRooms = gameDao.getLinkedRoom(player.getCurrentRoomId(), player.getPreviousRoomId());
            int extraDistance = playerDao.getPlayer(esn).getExtraDistance();
            for (LinkedRoom linkedRoom : linkedRooms) {
                linkedRoom.setDistance(linkedRoom.getDistance() + extraDistance);
            }
            return linkedRooms;
        }
    }

    @Override
    public Room gotoRoom(String esn, Integer roomId) {
        try {
            RoomWrapper roomWrapper = gameDao.gotoRoom(esn, roomId);
            Room room = roomWrapper.getRoom();
            RoomEvent roomEvent = room.getRoomEvent();
            EventType eventType = roomEvent.getEventType();
            EventContent eventContent = roomEvent.getEventContent();
            
            if (eventContent == null) {// EventContent would be null if the player has been there before.
            	gameDao.unLockPlayer(esn);
                return room;
            }
            
            if (eventType == EventType.QUESTION) {
                Message message = genQuestionResponse(roomEvent.getEventId(), eventContent);
                LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), message);
            } else {
                ActionContent actionContent = (ActionContent) roomWrapper.getEventWrapper().getRawObject();
                ActionEvent actionEvent = actionContent.getAction();
                switch (actionEvent) {
                    case CHANGE_SCORE:
                    case STOLE_SCORE: {
                        List<Message> messages = genActionResponse(roomEvent.getEventId(), eventContent);
                        for (Message message : messages) {
                            LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), message);
                        }
                        break;
                    }
                    case HIDE_EVENT: {
                        int randomRoomId = gameDao.getOneRandomRoomsThatPlayerNeverBeenTo(esn);
                        gameDao.addRoomRecord(esn, randomRoomId);
                        Message message = LineBotUtils.genTextMessage(eventContent.getEvent());
                        LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), message);
                        gameDao.unLockPlayer(esn);
                        break;
                    }
                    case BACK_TO_LOBBY: {
                        int currentRoomId = playerDao.getPlayerLocation(esn);
                        Room currentRoom = gameDao.getRoom(currentRoomId);
                        if (currentRoom.getRoomPhase() == 2) {
                            room = this.gotoRoom(esn, PHASE_TWO_STARTING_ROOM_ID);
                        } else {
                            room = this.gotoRoom(esn, PHASE_ONE_STARTING_ROOM_ID);
                        }
                        Message message = LineBotUtils.genTextMessage(eventContent.getEvent());
                        LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), message);
                        gameDao.unLockPlayer(esn);
                        break;
                    }
                    case ADD_STEPS: {
                        playerDao.increasePlayerExtraDistanceByEsn(esn, INCREMENTAL_STEPS);
                        Message message = LineBotUtils.genTextMessage(eventContent.getEvent());
                        LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), message);
                        gameDao.unLockPlayer(esn);
                        break;
                    }
                    case DOUBLE_SCORE: {
                        playerDao.doublePlayerScoreByEsn(esn);
                        Message message = LineBotUtils.genTextMessage(eventContent.getEvent());
                        LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), message);
                        gameDao.unLockPlayer(esn);
                        break;
                    }
                    case BOSS: {
                    	Message message = LineBotUtils.genTextMessage(eventContent.getEvent());
                        LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), message);
                        break;
                    }
                    default:
                        break;
                }
            }
            return room;
        } catch (Exception e) {
            return new Room();
        }
    }

    private Message genQuestionResponse(String eventId, EventContent eventContent) {
        List<Pair<String, String>> answers = new ArrayList<>();
        int count = 1;
        for (String option : eventContent.getEventOptions()) {
            answers.add(new ImmutablePair<>(option, Integer.toString(count)));
            count++;
        }
        return LineBotUtils.genQuestion("Here comes a question!", eventId, eventContent.getEvent(), answers);
    }

    private List<Message> genActionResponse(String eventId, EventContent eventContent) {
        List<Pair<String, String>> answers = new ArrayList<>();
        int count = 1;
        for (String option : eventContent.getEventOptions()) {
        	String [] items = option.split(":");
        	String esn = items[0];
        	String score = items[1];
            answers.add(new ImmutablePair<>(Integer.toString(count) + ". Team " + esn + ":" + score, esn));
            count++;
        }
        List<Message> messages = new ArrayList<>();
        messages.add(LineBotUtils.genQuestion("Here comes a question!", eventId, eventContent.getEvent(), answers.subList(0, 4)));
        messages.add(LineBotUtils.genQuestion("Here comes a question!", eventId, eventContent.getEvent(), answers.subList(4, 8)));
        return messages;
    }

    /**
     * @param esn
     * @return Pair<YourScore, BossScore>
     */
    public Pair<Integer, Integer> battleWithBoss(String esn) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        final int ORIGIN = 1;
        final int BOUND = 6;
        // Player's = current score * threadLocalRandom
        int playerScore = playerDao.getPlayerScoreByEsn(esn) *
                threadLocalRandom.nextInt(ORIGIN, BOUND);

        // Boss's score = median of all players * threadLocalRandom
        int bossScore = 0;
        List<Player> players = playerDao.getAllPlayers();
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getScore() - o1.getScore();
            }
        });

        int mid = players.size() / 2;
        if (players.size() % 2 == 1) {
            bossScore = players.get(mid).getScore();
        } else {
            bossScore = (int) Math.round((players.get(mid - 1).getScore() + players.get(mid).getScore()) / 2.0);
        }
        bossScore *= threadLocalRandom.nextInt(ORIGIN, BOUND);

        Pair<Integer, Integer> pair = Pair.of(playerScore, bossScore);
        return pair;
    }

}
