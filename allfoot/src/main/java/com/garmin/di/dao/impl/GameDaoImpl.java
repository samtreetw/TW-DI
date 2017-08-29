package com.garmin.di.dao.impl;

import com.garmin.di.dao.GameDao;
import com.garmin.di.dao.PlayerDao;
import com.garmin.di.dao.util.ResourceUtil;
import com.garmin.di.domain.GameStatus;
import com.garmin.di.domain.PlayerStatus;
import com.garmin.di.dto.EventContent;
import com.garmin.di.dto.EventType;
import com.garmin.di.dto.LinkedRoom;
import com.garmin.di.dto.Player;
import com.garmin.di.dto.Room;
import com.garmin.di.dto.RoomEvent;

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

    private static final String SQL_GET_PLAYER_LOCATION =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getPlayerLocation.sql"));

    private static final String SQL_GET_LINKED_ROOM =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getLinkedRoom.sql"));

    private static final String SQL_UPDATE_PLAYER_LOCATION =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/updatePlayerLocation.sql"));

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

    private static final String SQL_UPDATE_PLAYER_LINE_ID =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/updatePlayerLineId.sql"));

    private static final String SQL_GET_PLAYER_LINE_ID =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/getPlayerLineId.sql"));

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
        List<Integer> query = getJdbcTemplate().query(SQL_GET_PLAYER_LOCATION, new SingleColumnRowMapper<Integer>(), esn);
        return query.isEmpty() ? -1 : query.get(0);
    }

    @Override
    public boolean gotoRoom(String esn, int roomId) {
    	lockPlayer(esn);
        return getJdbcTemplate().update(SQL_UPDATE_PLAYER_LOCATION, roomId, esn) > 0;
    }

    @Override
    public Room getRoom(String esn, int roomId) {
    	
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
        Room room = rooms.isEmpty() ? null : rooms.get(0);
        
        // Check user room record. No event for the user who already been here before.
        List<Integer> records = getJdbcTemplate().query(SQL_GET_ROOM_PLAYER_RANK, new SingleColumnRowMapper<Integer>(), roomId, esn);
        if (room != null && records.size() == 0) {
        	
	        EventContent eventContents = getJdbcTemplate().query(SQL_GET_ROOM_QUESTION, new ResultSetExtractor<EventContent>() {

	        	EventContent eventContent = new EventContent();
				@Override
				public EventContent extractData(ResultSet resultSet) throws SQLException, DataAccessException {
					
					while(resultSet.next()) {
						if (StringUtils.isEmpty(eventContent.getEvent())) {
							eventContent.setEvent(resultSet.getString("question_text"));
						}
						eventContent.addEventOptions(resultSet.getString("options_text"));
					}
					return eventContent;
				}
				
			}, room.getRoomEvent().getEventId());
	        room.getRoomEvent().setEventContent(eventContents);
        }
        return room; 
    }
    
    @Override
	public boolean passRoom(String esn, int roomId) {
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
		Player player = playerDao.getPlayer(esn);
		int score = player.getScore() + rankScore;
		return playerDao.setPlayerScore(esn, score);
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
    public boolean updatePlayerLineId(String esn, String lineId) {
        return getJdbcTemplate().update(SQL_UPDATE_PLAYER_LINE_ID, lineId, esn) > 0;
    }

    @Override
    public String getPlayerLineId(String esn) {
        List<String> query = getJdbcTemplate().query(SQL_GET_PLAYER_LINE_ID, new SingleColumnRowMapper<String>(), esn);
        return query.isEmpty() ? "0" : query.get(0);
    }

    @Override
    public boolean isAdmin(String lineId) {
        List<Integer> query = getJdbcTemplate().query(SQL_CHECK_IS_ADMIN, new SingleColumnRowMapper<Integer>(), lineId);
        return query.get(0) != 0;
    }

	@Override
	public int getAnswer(int eventId) {
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
		return playerDao.setPlayerStatus(esn, PlayerStatus.LOCK);
	}

	@Override
	public boolean unLockPlayer(String esn) {
		return playerDao.setPlayerStatus(esn, PlayerStatus.FREE);
	}
    
}
