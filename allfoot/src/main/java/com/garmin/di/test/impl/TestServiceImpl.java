package com.garmin.di.test.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.garmin.di.dao.GameDao;
import com.garmin.di.dao.PlayerDao;
import com.garmin.di.test.TestService;

public class TestServiceImpl implements TestService {

	private GameDao gameDao;
    private PlayerDao playerDao;

    @Autowired
    public TestServiceImpl(GameDao gameDao, PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }
	
	@Override
	public boolean passRoom(String esn, Integer roomId) {
		return gameDao.passRoom(esn, roomId);
	}
	
}
