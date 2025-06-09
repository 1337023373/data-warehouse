package com.ggzed.im.common.factory;

import java.sql.SQLException;

/**
 * @Author lkj
 * @Date 2025/6/9 10:11
 * @Version 1.0
 */
public interface DatabaseConnectionStrategy {
    //测试数据库连接是否有效
    Boolean testConnection(String url, String username, String password) throws SQLException;
    //测试数据库连接是否有效
    String buildUrl(String host, Integer port, String database, String extraParams);
    //测试数据库连接是否有效
    String getDriverClass();
}
