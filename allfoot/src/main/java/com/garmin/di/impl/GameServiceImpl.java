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
import com.garmin.di.dto.enums.PlayerStatus;
import com.garmin.di.util.LineBotUtils;
import com.linecorp.bot.model.message.Message;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        if (player.getPlayerStatus() == PlayerStatus.LOCK) {
            return Collections.emptyList();
        } else {
            return gameDao.getLinkedRoom(player.getCurrentRoomId(), player.getPreviousRoomId());
        }
    }

    @Override
    public boolean gotoRoom(String esn, Integer roomId) {
    	try {
    		RoomWrapper roomWrapper = gameDao.gotoRoom(esn, roomId);
	        Room room = roomWrapper.getRoom(); 
	        RoomEvent roomEvent = room.getRoomEvent();
	        EventType eventType = roomEvent.getEventType();
	        if (eventType == EventType.QUESTION) {
		        Message message = genQuestionResponse(roomEvent.getEventId(), roomEvent.getEventContent());
		        LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), message);
	        } else {
	        	ActionContent actionContent = (ActionContent) roomWrapper.getEventWrapper().getRawObject();
	        	ActionEvent actionEvent = actionContent.getAction();
	        	switch (actionEvent) {
	        	case CHANGE_SCORE:
	    		case STOLE_SCORE: {
	    			Message message = genQuestionResponse(roomEvent.getEventId(), roomEvent.getEventContent());
			        LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), message);
			        break;
	    		}
	    		case HIDE_EVENT: {
	    			int randomRoomId = gameDao.getOneRandomRoomsThatPlayerNeverBeenTo(esn);
	    			gameDao.addRoomRecord(esn, randomRoomId);
	    			Message message = LineBotUtils.genTextMessage(roomEvent.getEventContent().getEvent());
	    			LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), message);
	    			break;
	    		}
	    		case BACK_TO_LOBBY: {
	    			
	    		}
	    		case ADD_STEPS: {
	    			
	    		}
	    		case DOUBLE_SCORE: {
	    			playerDao.doublePlayerScoreByEsn(esn);
	    			Message message = LineBotUtils.genTextMessage(roomEvent.getEventContent().getEvent());
	    			LineBotUtils.sendPushMessage(playerDao.getPlayerLineId(esn), message);
					break;
	    		}
				default:
					break;
				}
	        }
	        return true;
    	} catch (Exception e) {
    		return false;
		}
    }
    
    private Message genQuestionResponse(String eventId, EventContent eventContent) {
        List<Pair<String, String>> answers = new ArrayList<>();
        int count = 1;
        for (String option: eventContent.getEventOptions()) {
        	answers.add(new ImmutablePair<>(option, Integer.toString(count)));
        	count++;
        }
        return LineBotUtils.genQuestion("Here comes a question!", eventId, eventContent.getEvent(), answers);
    }

}
