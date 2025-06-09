package com.ggzed.im.database;

import com.ggzed.im.common.factory.DatabaseConnectionStrategy;
import com.ggzed.im.common.factory.DatabaseConnectionStrategyFactory;
import com.ggzed.im.model.req.RepositoryConfigReq;
import com.zaxxer.hikari.HikariConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author lkj
 * @Date 2025/6/9 16:37
 * @Version 1.0
 */
@Component
public class DynamicDataSourceManager {
    @Resource
    private DatabaseConnectionStrategyFactory strategyFactory;

    private final Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();
    public Boolean addDataSource(String key, RepositoryConfigReq req) {
        HikariConfig hikariConfig = new HikariConfig();
        DatabaseConnectionStrategy strategy = strategyFactory.getStrategy(req.getRepoType());
        if (strategy== null) {
            return false;
        }
        String url = strategy.buildUrl(req.getHost(), req.getPort(), req.getDatabaseName(), req.getConnectionParams());
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(req.getUsername());
        hikariConfig.setPassword(req.getPassword());
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setIdleTimeout(300000);
        hikariConfig.setKeepaliveTime(300000);
        hikariConfig.setMaxLifetime(1800000);
        return true;
    }

    public DataSource getDataSource(String key) {
        return dataSourceMap.get(key);
    }

    public boolean exists(String key) {
        return dataSourceMap.containsKey(key);
    }

}
