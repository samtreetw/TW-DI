package com.garmin.di.dao;

import com.garmin.di.dto.LinkedRoom;
import com.garmin.di.dto.Room;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/5
 * Time: 19:44
 */
public interface GameDao {

    boolean joinGame(String esn);

    List<LinkedRoom> getLinkedRoom(int roomId);

    int getCurrentRoom(String esn);

    boolean gotoRoom(String esn, int roomId);

    Room getRoom(int roomId);

    boolean updateGameStatus(int gameStatusId);

    boolean addGameRecord(String esn, int roomId);

    boolean addAdmin(String name, String lineId);

    boolean updatePlayerLineId(String esn, String lineId);

    String getPlayerLineId(String esn);

    boolean isAdmin(String lineId);
}
