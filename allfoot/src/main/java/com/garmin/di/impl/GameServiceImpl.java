package com.garmin.di.impl;

import com.garmin.di.GameService;
import com.garmin.di.dao.GameDao;
import com.garmin.di.dao.PlayerDao;
import com.garmin.di.domain.PlayerStatus;
import com.garmin.di.dto.LinkedRoom;
import com.garmin.di.dto.Player;
import com.garmin.di.dto.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import java.util.Collections;
import java.util.List;

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
    public List<LinkedRoom> getLinked(String esn) {
        Player player = playerDao.getPlayer(esn);
        if (player.getPlayerStatus() == PlayerStatus.LOCK) {
            return Collections.emptyList();
        } else {
            return gameDao.getLinkedRoom(player.getCurrentRoomId(), player.getPreviousRoomId());
        }
    }

    @Override
    public Room gotoRoom(String esn, Integer roomId) {
        gameDao.gotoRoom(esn, roomId);
        return gameDao.getRoom(roomId);
    }

}
