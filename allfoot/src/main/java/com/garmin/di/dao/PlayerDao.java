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

    String getPlayerEsnByLineId(String lineId);
    
    int getPlayerCounts();
    
    /***************
     * Setter
     ***************/
    boolean updatePlayerLineId(String esn, String lineId);
    
    boolean updatePlayerLocation(String esn, int roomId);
    
    boolean updatePlayerScore(String esn, int score);
    
    /**
     * 
     * @param esn
     * @param playerStatus {@link PlayerStatus}
     * @return
     */
    boolean updatePlayerStatus(String esn, @NotNull PlayerStatus playerStatus);

    boolean switchPlayersScores(String triggeringLineId, String victimEsn);

    boolean doublePlayerScoreByEsn(String esn);

    boolean stealPlayerScore(String stealerLineId, String victimEsn, Integer score);

    List<String> getAllPlayerScores();

    boolean increasePlayerExtraDistanceByEsn(String esn, int increment);

    boolean reducePlayerExtraDistanceByEsn(String esn, int increment);
}
