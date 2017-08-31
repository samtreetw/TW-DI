package com.garmin.di.dao.impl;

import com.garmin.di.dao.GameDao;
import com.garmin.di.dao.PlayerDao;
import com.garmin.di.dao.util.EventWrapper;
import com.garmin.di.dao.util.ResourceUtil;
import com.garmin.di.dao.util.RoomWrapper;
import com.garmin.di.dto.ActionContent;
import com.garmin.di.dto.EventContent;
import com.garmin.di.dto.EventContentImp;
import com.garmin.di.dto.LinkedRoom;
import com.garmin.di.dto.Player;
import com.garmin.di.dto.Room;
import com.garmin.di.dto.RoomEvent;
import com.garmin.di.dto.enums.ActionEvent;
import com.garmin.di.dto.enums.EventType;
import com.garmin.di.dto.enums.GameStatus;
import com.garmin.di.dto.enums.PlayerStatus;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/5
 * Time: 19:47
 */
@Repository
public class GameDaoImpl extends NamedParameterJdbcDaoSupport implements GameDao {

    private static final String SQL_PLAYER_JOIN =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/playerJoin.sql"));

    private static final String SQL_GET_LINKED_ROOM =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getLinkedRoom.sql"));

    private static final String SQL_GET_ROOM =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getRoom.sql"));

    private static final String SQL_INSERT_ROOM_RECORD =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/insertRoomRecord.sql"));
    
    private static final String SQL_GET_ROOM_PLAYER_RANK =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getRoomPlayerRank.sql"));

    private static final String SQL_UPDATE_GAME_STATUS =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/updateGameStatus.sql"));

    private static final String SQL_INSERT_ADMIN =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/insertAdmin.sql"));

    private static final String SQL_CHECK_IS_ADMIN =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/checkIsAdmin.sql"));
    
    private static final String SQL_GET_ROOM_QUESTION =
    		ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getRoomQuestion.sql"));
    
    private static final String SQL_GET_ANSWER =
    		ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getAnswer.sql"));

    private static final String SQL_GET_ROOM_LAST_RANK =
    		ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getRoomLastRank.sql"));
    
    private static final String SQL_GET_RANK_SCORE =
    		ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getRankScore.sql"));
    
    private static final String SQL_POP_ACTION_QUEUE =
    		ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getActionFromQueue.sql"));
    
    private static final String SQL_PUT_ACTION_QUEUE =
    		ResourceUtil.readFileContents(new ClassPathResource("/sql/game/insertActionQueue.sql"));
    
    private static final String SQL_GET_ROOM_ACTION =
    		ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getRoomAction.sql"));
    
    private PlayerDao playerDao;
    
    
    @Autowired
    public GameDaoImpl(@Qualifier("dataSource") DataSource dataSource, PlayerDao playerDao) {
        super.setDataSource(dataSource);
        this.playerDao = playerDao;
    }

    @Override
    public boolean joinGame(String esn) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("esn", esn);
        return getNamedParameterJdbcTemplate().update(SQL_PLAYER_JOIN, source) > 0;
    }

    @Override
    public List<LinkedRoom> getLinkedRoom(int roomId, int excludeRoomId) {
        return getJdbcTemplate().query(SQL_GET_LINKED_ROOM, new RowMapper<LinkedRoom>() {
            @Override
            public LinkedRoom mapRow(ResultSet resultSet, int i) throws SQLException {
                LinkedRoom lr = new LinkedRoom();
                lr.setRoomId(resultSet.getInt("room_id_to"));
                lr.setDistance(resultSet.getInt("distance"));
                lr.setRoomName(resultSet.getString("name"));
                return lr;
            }
        }, roomId, excludeRoomId);
    }

    @Override
    public int getCurrentRoom(String esn) {
       return playerDao.getPlayerLocation(esn);
    }

    @Override
    public RoomWrapper gotoRoom(String esn, int roomId) {
    	
    	lockPlayer(esn);
        
    	playerDao.updatePlayerLocation(esn, roomId);
        
    	Room room = getRoom(roomId);
    	
    	if (room == null) {
    		return null;
    	}
    	
    	RoomWrapper roomWrapper = new RoomWrapper(room);
    	if (!hasPlayerBeenTo(esn, roomId)) {
	        roomWrapper.setEventWrapper(genEventWrapper(room.getRoomEvent().getEventType(), room.getRoomEvent().getEventId()));
        }
        
        return roomWrapper;
    }
    
    
    private EventWrapper genEventWrapper(final EventType eventType, final String eventId) {
    	if (eventType == EventType.QUESTION) {
    		EventContentImp questionContent = getJdbcTemplate().query(SQL_GET_ROOM_QUESTION, new ResultSetExtractor<EventContentImp>() {
	
				@Override
				public EventContentImp extractData(ResultSet resultSet) throws SQLException, DataAccessException {
					EventContentImp questionContent = new EventContentImp();
					ArrayList<String> list = new ArrayList<String>();
					while(resultSet.next()) {
						if (StringUtils.isEmpty(questionContent.getEvent())) {
							questionContent.setEvent(resultSet.getString("question_text"));
						}
						list.add(resultSet.getString("options_text"));
					}
					questionContent.setEventOptions(list);
					return questionContent;
				}
				
			}, eventId);
    		return new EventWrapper<EventContentImp>(questionContent);
    	} else {
    		List<ActionContent> actionContents = getJdbcTemplate().query(SQL_GET_ROOM_ACTION, new RowMapper<ActionContent>() {

				@Override
				public ActionContent mapRow(ResultSet rs, int rowNum) throws SQLException {
					ActionContent actionContent = new ActionContent(playerDao, ActionEvent.getByName(eventId), rs.getString("action_text_a"), rs.getString("action_text_b"));
					return actionContent;
				}
    		}, eventId);
    		return new EventWrapper<ActionContent>(actionContents.get(0));
    	}
    }
    
    @Override
    public boolean hasPlayerBeenTo(String esn, int roomId) {
    	// Check user room record. No event for the user who already been here before.
        List<Integer> records = getJdbcTemplate().query(SQL_GET_ROOM_PLAYER_RANK, new SingleColumnRowMapper<Integer>(), roomId, esn);
        return records.size() != 0; 
    }

    @Nullable
    @Override
    public Room getRoom(int roomId) {
    	
        List<Room> rooms = getJdbcTemplate().query(SQL_GET_ROOM, new RowMapper<Room>() {
            @Override
            public Room mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Room room = new Room();
                RoomEvent roomEvent = new RoomEvent();
                room.setRoomId(resultSet.getInt("room_id"));
                room.setName(resultSet.getString("name"));
                room.setDesc(resultSet.getString("description"));
                roomEvent.setEventId(resultSet.getString("event_id"));
                roomEvent.setEventType(EventType.values()[resultSet.getInt("event_type")]);
                room.setRoomEvent(roomEvent);
                return room;
            }
        }, roomId);
        return rooms.isEmpty() ? null : rooms.get(0);
    }
    
    @Override
	public boolean passRoom(String esn, int roomId) {
        if (roomId == -1) {
            return false;
        }
    	//TODO checkQueue.

    	// Calculate score.
    	int lastRank = getJdbcTemplate().query(SQL_GET_ROOM_LAST_RANK, new ResultSetExtractor<Integer>(){

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				int rank = 0;
				while (rs.next()) {
					rank = rs.getInt("rank");
				}
				return rank;
			}
			
		}, roomId);
		++lastRank;
		// Get score of rank.
		int rankScore = getJdbcTemplate().query(SQL_GET_RANK_SCORE, new ResultSetExtractor<Integer>(){

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				int rankScore = 0;
				while (rs.next()) {
					rankScore = rs.getInt("score");
				}
				return rankScore;
			}
		}, lastRank);
		// Update user score.
		int score = playerDao.getPlayerScore(esn) + rankScore;
		return playerDao.updatePlayerScore(esn, score);
	}

    @Override
    public boolean updateGameStatus(GameStatus gameStatus) {
        return getJdbcTemplate().update(SQL_UPDATE_GAME_STATUS, gameStatus.getId()) > 0;
    }

    @Override
    public boolean addGameRecord(String esn, int roomId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("room_id", roomId);
        source.addValue("esn", esn);
        return getNamedParameterJdbcTemplate().update(SQL_INSERT_ROOM_RECORD, source) > 0;
    }

    @Override
    public boolean addAdmin(String name, String lineId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", name);
        source.addValue("line_id", lineId);
        return getNamedParameterJdbcTemplate().update(SQL_INSERT_ADMIN, source) > 0;
    }

    @Override
    public boolean isAdmin(String lineId) {
        List<Integer> query = getJdbcTemplate().query(SQL_CHECK_IS_ADMIN, new SingleColumnRowMapper<Integer>(), lineId);
        return query.get(0) != 0;
    }

	@Override
	public int getAnswer(String eventId) {
		List<Integer> answerIds = getJdbcTemplate().query(SQL_GET_ANSWER, new SingleColumnRowMapper<Integer>(), eventId);
		if (answerIds.size() == 0) {
			return 0;
		} else if (answerIds.get(0) == null) {
			return 0;
		} else {
			return answerIds.get(0);
		}
	}

	@Override
	public boolean lockPlayer(String esn) {
		return playerDao.updatePlayerStatus(esn, PlayerStatus.LOCK);
	}

	@Override
	public boolean unLockPlayer(String esn) {
		return playerDao.updatePlayerStatus(esn, PlayerStatus.FREE);
	}

	@Override
	public boolean putActionQueue(String esn, @NotNull ActionEvent actionEvent) {
		return getJdbcTemplate().update(SQL_PUT_ACTION_QUEUE, esn, actionEvent.getName()) > 0;
	}

	@Override
	public List<String> popActionQueue(String esn) {
		List<String> actions = getJdbcTemplate().query(SQL_POP_ACTION_QUEUE, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("action_id");
			}
			
		}, esn);
		return actions;
	}
    
}
