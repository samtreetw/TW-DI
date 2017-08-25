package com.garmin.di.dao;

import com.garmin.di.dto.Player;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/24
 * Time: 16:52
 */
public interface PlayerDao {

    Player getPlayer(String esn);

    boolean setPlayerStatus(String esn, int playerStatusId);

}
