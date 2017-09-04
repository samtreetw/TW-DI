package com.garmin.di.dao;

import com.garmin.di.dao.util.RoomWrapper;
import com.garmin.di.dto.ActionContent;
import com.garmin.di.dto.EventContentImp;
import com.garmin.di.dto.LinkedRoom;
import com.garmin.di.dto.Room;
import com.garmin.di.dto.enums.ActionEvent;
import com.garmin.di.dto.enums.GameStatus;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/5
 * Time: 19:44
 */
public interface GameDao {

    boolean joinGame(String esn);

    List<LinkedRoom> getLinkedRoom(int roomId, int excludeRoomId);

    int getCurrentRoom(String esn);

    /**
     * Get {@link Room} Object but without {@link com.garmin.di.dto.EventContentImp}
     * @param roomId
     * @return
     */
    @Nullable Room getRoom(int roomId);
    
    boolean hasPlayerBeenTo(String esn, int roomId);
    
    int getOneRandomRoomsThatPlayerNeverBeenTo(String esn);
    
    RoomWrapper gotoRoom(String esn, int roomId);
    
    boolean passRoom(String esn, int roomId);

    boolean updateGameStatus(@NotNull GameStatus gameStatus);

    boolean addGameRecord(String esn, int roomId);

    List<Integer> getGameRank(String esn, int roomId);

    boolean addRoomRecord(String esn, int roomId);

    boolean addAdmin(String name, String lineId);

    boolean isAdmin(String lineId);
    
    int getAnswer(String eventId);
    
    boolean lockPlayer(String esn);
    
    boolean unLockPlayer(String esn);
    
    boolean putActionQueue(String esn, @NotNull ActionEvent actionEvent);
    
    List<String> popActionQueue(String esn);
    
    EventContentImp getQuestion(String eventId);
    
    ActionContent getAction(String eventId);
    
}
