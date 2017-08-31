package com.garmin.di.test.impl;


import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garmin.di.dao.GameDao;
import com.garmin.di.dao.PlayerDao;
import com.garmin.di.dto.Room;
import com.garmin.di.test.TestService;

@Path("/test-service")
@Service
public class TestServiceImpl implements TestService {

	private GameDao gameDao;
    private PlayerDao playerDao;

    @Autowired
    public TestServiceImpl(GameDao gameDao, PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }
	
	@Override
	public boolean passRoomTest(String esn, Integer roomId) {
		return gameDao.passRoom(esn, roomId);
	}
	
	@Override
	public Room gotoRoomTest(String esn, Integer roomId) {
		return gameDao.gotoRoom(esn, roomId).getRoom(); 
	}
	
}
