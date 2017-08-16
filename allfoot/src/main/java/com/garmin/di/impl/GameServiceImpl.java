package com.garmin.di.impl;

import com.garmin.di.GameService;
import com.garmin.di.dao.GameDao;
import com.garmin.di.dto.LinkedRoom;
import com.garmin.di.dto.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
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

    @Autowired
    public GameServiceImpl(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public String join(String esn) {
        return String.valueOf(gameDao.joinGame(esn));
    }

    @Override
    public List<LinkedRoom> getLinked(String esn) {
        int currentRoomId = gameDao.getCurrentRoom(esn);
        return gameDao.getLinkedRoom(currentRoomId);
    }

    @Override
    public Room gotoRoom(String esn, Integer roomId) {
        gameDao.gotoRoom(esn, roomId);
        return gameDao.getRoom(roomId);
    }
}
