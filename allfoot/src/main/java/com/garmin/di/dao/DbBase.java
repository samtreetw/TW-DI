package com.garmin.di.dao;

import com.garmin.di.dao.util.ResourceUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

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

    private static final String SQL_CREATE_GAME =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/initialdb/createGame.sql"));

    private static final String SQL_CHECK_INITIAL =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/initialdb/checkIfInitial.sql"));

    private static final String SQL_INSERT_GAME =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/initialdb/insertGame.sql"));

    private static final String SQL_GET_DROP_TABLES_SQL =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/initialdb/getDropTablesSql.sql"));

    private static final String SQL_RESET_STATUS =
            ResourceUtil.readFileContents(new ClassPathResource("/sql/game/resetGame.sql"));

    @Autowired
    public DbBase(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);

        setup();
    }

    public void setup() {
        createTables();
        insertConstantsData();
    }

    public void reset() {
        getJdbcTemplate().update(SQL_RESET_STATUS);
    }

    public void drop() {
        List<String> sqls = getJdbcTemplate().query(SQL_GET_DROP_TABLES_SQL, new SingleColumnRowMapper<String>());
        for (String dropSql : sqls) {
            if (StringUtils.containsIgnoreCase(dropSql, "admin_user")) {
                // Preserve admin_user table when resetting DB
                continue;
            }
            getJdbcTemplate().update(dropSql);
        }
    }

    private void insertConstantsData() {
        List<Integer> count = getJdbcTemplate().query(SQL_CHECK_INITIAL, new SingleColumnRowMapper<Integer>());
        if (count.get(0) == 0) {
            getJdbcTemplate().update(SQL_INSERT_GAME);
        }
    }

    private void createTables() {
        getJdbcTemplate().update(SQL_CREATE_GAME);
    }
}
