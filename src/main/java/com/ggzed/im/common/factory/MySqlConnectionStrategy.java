package com.ggzed.im.common.factory;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author lkj
 * @Date 2025/6/9 10:15
 * @Version 1.0
 */
@Component
public class MySqlConnectionStrategy implements DatabaseConnectionStrategy {
    @Override
    public Boolean testConnection(String url, String username, String password) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection.isValid(5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String buildUrl(String host, Integer port, String database, String extraParams) {
        String url = String.format("jdbc:mysql://%s:%s/%s", host, port, database);
        if (StringUtils.isNotBlank(extraParams)) {
            url = url + "?" + extraParams;
        }
        return url;
    }

    @Override
    public String getDriverClass() {
        return "com.mysql.cj.jdbc.Driver";
    }
}
