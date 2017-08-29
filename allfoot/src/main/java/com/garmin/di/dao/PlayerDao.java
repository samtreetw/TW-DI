package com.garmin.di.dao;

import com.garmin.di.dto.Player;
import com.garmin.di.dto.enums.PlayerStatus;
import com.sun.istack.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/24
 * Time: 16:52
 */
public interface PlayerDao {

    /***************
     * Getter
     ***************/
	Player getPlayer(String esn);
    
    String getPlayerLineId(String esn);
    
    int getPlayerLocation(String esn);
    
    int getPlayerScore(String esn);
    
    PlayerStatus getPlayerStatus(String esn);

    
    
    /***************
     * Setter
     ***************/
    boolean updatePlayerLineId(String esn, String lineId);
    
    boolean updatePlayerLocation(String esn, int roomId);
    
    boolean updatePlayerScore(String esn, int score);
    
    /**
     * 
     * @param esn
     * @param playerStatusId 0:unlock 1:lock
     * @return
     */
    boolean updatePlayerStatus(String esn, @NotNull PlayerStatus playerStatus);
    
    
    
    
    
    
}
