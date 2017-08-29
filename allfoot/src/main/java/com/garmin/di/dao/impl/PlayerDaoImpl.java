package com.garmin.di.dao.impl;

import com.garmin.di.dao.PlayerDao;
import com.garmin.di.dao.util.ResourceUtil;
import com.garmin.di.domain.PlayerStatus;
import com.garmin.di.dto.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;
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

    private static final String SQL_SET_PLAYER_STATUS =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/player/updatePlayerStatus.sql"));
    
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
                return player;
            }
        }, esn);
        return players.isEmpty() ? null : players.get(0);
    }

    @Override
    public boolean setPlayerStatus(String esn, PlayerStatus playerStatus) {
        return getJdbcTemplate().update(SQL_SET_PLAYER_STATUS, playerStatus.getId(), esn) > 0;
    }

}
