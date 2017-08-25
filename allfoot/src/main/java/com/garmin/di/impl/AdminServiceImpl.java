package com.garmin.di.impl;

import com.garmin.di.AdminService;
import com.garmin.di.dao.DbBase;
import com.garmin.di.dao.GameDao;
import com.garmin.di.dao.PlayerDao;
import com.garmin.di.domain.GameStatus;
import com.garmin.di.domain.PlayerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/15
 * Time: 10:04
 */
@Path("/admin-service")
@Service
public class AdminServiceImpl implements AdminService {

    private DbBase dbBase;
    private GameDao gameDao;
    private PlayerDao playerDao;

    @Autowired
    public AdminServiceImpl(DbBase dbBase, GameDao gameDao, PlayerDao playerDao) {
        this.dbBase = dbBase;
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    @Override
    public void resetDb() {
        dbBase.drop();
        dbBase.setup();
    }

    @Override
    public void resetGame() {
        dbBase.reset();
    }

    @Override
    public boolean setGameStatus(String status) {
        return gameDao.updateGameStatus(GameStatus.valueOf(status).getId());
    }

    @Override
    public boolean updatePlayerStatus(String esn, String status) {
        return playerDao.setPlayerStatus(esn, PlayerStatus.valueOf(status).getId());
    }

    @Override
    public boolean addRoomRecord(String esn, int roomId) {
        return gameDao.addGameRecord(esn, roomId);
    }
}
