package com.garmin.di.dao;

import com.garmin.di.dao.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/5
 * Time: 18:56
 */
@Component
public class DbBase extends JdbcDaoSupport {

    private static final String SQL_CREATE_ECHO =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/createEcho.sql"));
    private static final String SQL_CHECK_ECHO =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/checkEcho.sql"));
    private static final String SQL_INSERT_ECHO =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/insertEcho.sql"));

    private static final String SQL_CREATE_GAME =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/createGame.sql"));
    private static final String SQL_CHECK_INITIAL =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/checkIfInitial.sql"));
    private static final String SQL_INSERT_GAME =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/insertGame.sql"));


    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @PostConstruct
    private void init() {
        super.setDataSource(dataSource);

        createTables();
        insertConstantsData();
    }

    private void insertConstantsData() {
        List<Integer> count = getJdbcTemplate().query(SQL_CHECK_INITIAL, new SingleColumnRowMapper<Integer>());
        if (count.get(0) == 0) {
            getJdbcTemplate().update(SQL_INSERT_ECHO);
            getJdbcTemplate().update(SQL_INSERT_GAME);
        }
    }

    private void createTables() {
        getJdbcTemplate().update(SQL_CREATE_ECHO);
        getJdbcTemplate().update(SQL_CREATE_GAME);
    }
}
