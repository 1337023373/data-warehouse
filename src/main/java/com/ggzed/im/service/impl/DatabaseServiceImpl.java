package com.ggzed.im.service.impl;

import com.ggzed.im.common.factory.DatabaseConnectionStrategy;
import com.ggzed.im.common.factory.DatabaseConnectionStrategyFactory;
import com.ggzed.im.common.utils.BcryptUtil;
import com.ggzed.im.model.entity.RepositoryConfig;
import com.ggzed.im.model.req.RepositoryConfigReq;
import com.ggzed.im.repository.DatabaseRepository;
import com.ggzed.im.service.DatabaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;

/**
 * @Author lkj
 * @Date 2025/6/9 9:37
 * @Version 1.0
 */
@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Resource
    private DatabaseConnectionStrategyFactory strategyFactory;
    @Resource
    private DatabaseRepository databaseRepository;

    @Override
    public Boolean saveDatabase(RepositoryConfigReq req) throws SQLException {
        Boolean res = testDatabase(req);
        if (!res) {
            return false;
        }
        RepositoryConfig config = new RepositoryConfig();
        BeanUtils.copyProperties(req, config);
        config.setPassword(BcryptUtil.doEncrypt(req.getPassword()));
        config.setStatus(0);
        config.setLastTestTime(new Date());
        return databaseRepository.save(config);
    }

    @Override
    public Boolean testDatabase(RepositoryConfigReq req) throws SQLException {
        DatabaseConnectionStrategy strategy = strategyFactory.getStrategy(req.getRepoType());

        //加载驱动
        try {
            Class.forName(strategy.getDriverClass());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //构建连接url
        String url = strategy.buildUrl(req.getHost(), req.getPort(), req.getDatabaseName(), req.getConnectionParams());

        return strategy.testConnection(url, req.getUsername(), req.getPassword());
    }
}
