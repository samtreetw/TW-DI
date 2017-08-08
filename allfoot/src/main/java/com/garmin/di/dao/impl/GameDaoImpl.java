package com.garmin.di.dao.impl;

import com.garmin.di.dao.GameDao;
import com.garmin.di.dao.util.ResourceUtil;
import com.garmin.di.dto.LinkedRoom;
import com.garmin.di.dto.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
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

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @PostConstruct
    private void init() {
        super.setDataSource(dataSource);
    }

    @Override
    public boolean joinGame(String esn) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("esn", esn);
        return getNamedParameterJdbcTemplate().update(SQL_PLAYER_JOIN, source) > 0;
    }

    @Override
    public List<LinkedRoom> getLinkedRoom(int roomId) {
        return getJdbcTemplate().query(SQL_GET_LINKED_ROOM, new RowMapper<LinkedRoom>() {
            @Override
            public LinkedRoom mapRow(ResultSet resultSet, int i) throws SQLException {
                LinkedRoom lr = new LinkedRoom();
                lr.setRoomId(resultSet.getInt("room_id_to"));
                lr.setDistance(resultSet.getInt("distance"));
                return lr;
            }
        }, roomId);
    }

    @Override
    public int getCurrentRoom(String esn) {
        List<Integer> query = getJdbcTemplate().query(SQL_GET_PLAYER_LOCATION, new SingleColumnRowMapper<Integer>(), esn);
        return query.isEmpty() ? -1 : query.get(0);
    }

    @Override
    public boolean gotoRoom(String esn, int roomId) {
        return getJdbcTemplate().update(SQL_UPDATE_PLAYER_LOCATION, roomId, esn) > 0;
    }

    @Override
    public Room getRoom(int roomId) {
        List<Room> rooms = getJdbcTemplate().query(SQL_GET_ROOM, new RowMapper<Room>() {
            @Override
            public Room mapRow(ResultSet resultSet, int i) throws SQLException {
                Room room = new Room();
                room.setRoomId(resultSet.getInt("room_id"));
                room.setName(resultSet.getString("name"));
                room.setDesc(resultSet.getString("description"));
                return room;
            }
        }, roomId);
        return rooms.isEmpty() ? null : rooms.get(0);
    }
}
