package com.garmin.di.dao;

import com.garmin.di.domain.PlayerStatus;
import com.garmin.di.dto.Player;
import com.sun.istack.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/24
 * Time: 16:52
 */
public interface PlayerDao {

    Player getPlayer(String esn);

    /**
     * 
     * @param esn
     * @param playerStatusId 0:unlock 1:lock
     * @return
     */
    boolean setPlayerStatus(String esn, @NotNull PlayerStatus playerStatus);
    
    boolean setPlayerScore(String esn, int score);
    
}
