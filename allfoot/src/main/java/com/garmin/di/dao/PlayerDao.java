package com.garmin.di.dao;

import java.util.List;

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
	
	List<Player> getAllPlayers();
    
    String getPlayerLineId(String esn);
    
    int getPlayerLocation(String esn);
    
    int getPlayerScoreByEsn(String esn);
    
    int getPlayerScoreByLineId(String lineId);
    
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
     * @param playerStatus 0:unlock 1:lock
     * @return
     */
    boolean updatePlayerStatus(String esn, @NotNull PlayerStatus playerStatus);

    boolean switchPlayersScores(String triggeringLineId, String victimEsn);

    boolean doublePlayerScoreByLineId(String lineId);

    boolean stealPlayerScore(String stealerLineId, String victimEsn, Integer score);


    List<String> getAllPlayerScores();
}
