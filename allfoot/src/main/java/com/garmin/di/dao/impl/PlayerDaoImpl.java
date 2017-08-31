package com.garmin.di.dao.impl;

import com.garmin.di.dao.PlayerDao;
import com.garmin.di.dao.util.ResourceUtil;
import com.garmin.di.dto.Player;
import com.garmin.di.dto.enums.PlayerStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/24
 * Time: 16:54
 */
@Repository
public class PlayerDaoImpl extends NamedParameterJdbcDaoSupport implements PlayerDao {

    private static final String SQL_GET_PLAYER =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/getPlayer.sql"));

    private static final String SQL_GET_ALL_PLAYER =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/getAllPlayers.sql"));
    
    private static final String SQL_UPDATE_PLAYER_STATUS =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/updatePlayerStatus.sql"));
    
    private static final String SQL_GET_PLAYER_STATUS =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/getPlayerStatus.sql"));
    
    private static final String SQL_UPDATE_PLAYER_SCORE =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/updatePlayerScore.sql"));
    
    private static final String SQL_GET_PLAYER_SCORE_BY_ESN =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/getPlayerScoreByEsn.sql"));
    
    private static final String SQL_GET_PLAYER_SCORE_BY_LINE_ID =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/getPlayerScoreByLineId.sql"));
    
    private static final String SQL_UPDATE_PLAYER_LINE_ID =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/updatePlayerLineId.sql"));

    private static final String SQL_GET_PLAYER_LINE_ID =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/getPlayerLineId.sql"));
    
    private static final String SQL_UPDATE_PLAYER_LOCATION =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/updatePlayerLocation.sql"));
    
    private static final String SQL_GET_PLAYER_LOCATION =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/getPlayerLocation.sql"));
    
    @Autowired
    public PlayerDaoImpl(@Qualifier("dataSource") DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public Player getPlayer(String esn) {
        List<Player> players = getJdbcTemplate().query(SQL_GET_PLAYER, new RowMapper<Player>() {
            @Override
            public Player mapRow(ResultSet rs, int i) throws SQLException {
                Player player = new Player();
                player.setCurrentRoomId(rs.getInt("location"));
                player.setPreviousRoomId(rs.getInt("previous_location"));
                player.setPlayerStatus(PlayerStatus.lookup(rs.getInt("status")));
                player.setLineId(rs.getString("line_id"));
                player.setScore(rs.getInt("score"));
                return player;
            }
        }, esn);
        return players.isEmpty() ? null : players.get(0);
    }
    
    @Override
    public List<Player> getAllPlayers() {
    	return getJdbcTemplate().query(SQL_GET_ALL_PLAYER,new RowMapper<Player>() {

			@Override
			public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
				Player player = new Player();
                player.setCurrentRoomId(rs.getInt("location"));
                player.setPreviousRoomId(rs.getInt("previous_location"));
                player.setPlayerStatus(PlayerStatus.lookup(rs.getInt("status")));
                player.setLineId(rs.getString("line_id"));
                player.setScore(rs.getInt("score"));
                return player;
			}		
    	});
    }

    @Override
    public boolean updatePlayerStatus(String esn, PlayerStatus playerStatus) {
        return getJdbcTemplate().update(SQL_UPDATE_PLAYER_STATUS, playerStatus.getId(), esn) > 0;
    }

	@Override
	public boolean updatePlayerScore(String esn, int score) {
		return getJdbcTemplate().update(SQL_UPDATE_PLAYER_SCORE, score, esn) > 0;
	}
    
	@Override
    public int getPlayerLocation(String esn) {
    	 List<Integer> query = getJdbcTemplate().query(SQL_GET_PLAYER_LOCATION, new SingleColumnRowMapper<Integer>(), esn);
         return query.isEmpty() ? -1 : query.get(0);
    }

	@Override
    public boolean updatePlayerLocation(String esn, int roomId) {
        return getJdbcTemplate().update(SQL_UPDATE_PLAYER_LOCATION, roomId, esn) > 0;
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
	public int getPlayerScoreByEsn(String esn) {
		List<Integer> query = getJdbcTemplate().query(SQL_GET_PLAYER_SCORE_BY_ESN, new SingleColumnRowMapper<Integer>(), esn);
        return query.isEmpty() ? 0 : query.get(0);
	}
	
	@Override
	public int getPlayerScoreByLineId(String lineId) {
		List<Integer> query = getJdbcTemplate().query(SQL_GET_PLAYER_SCORE_BY_LINE_ID, new SingleColumnRowMapper<Integer>(), lineId);
        return query.isEmpty() ? 0 : query.get(0);
	}

	@Override
	public PlayerStatus getPlayerStatus(String esn) {
		List<Integer> query = getJdbcTemplate().query(SQL_GET_PLAYER_STATUS, new SingleColumnRowMapper<Integer>(), esn);
        return query.isEmpty() ? PlayerStatus.FREE : PlayerStatus.lookup(query.get(0));
	}
	
}
