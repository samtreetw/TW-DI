package com.garmin.di.dao.impl;

import com.garmin.di.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2016/4/6
 * Time: 14:04
 */
@Repository
public class DaoImpl extends JdbcDaoSupport implements Dao {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @PostConstruct
    private void init() {
        super.setDataSource(dataSource);
    }

    @Override
    public List<String> getAll() {
        return getJdbcTemplate().query("SELECT * FROM echo", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("text_value");
            }
        });
    }
}
