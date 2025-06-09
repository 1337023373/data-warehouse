package com.ggzed.im.service;

import com.ggzed.im.model.req.RepositoryConfigReq;

import java.sql.SQLException;

/**
 * @Author lkj
 * @Date 2025/6/9 9:37
 * @Version 1.0
 */
public interface DatabaseService {

    Boolean saveDatabase(RepositoryConfigReq req) throws SQLException;

    Boolean testDatabase(RepositoryConfigReq req) throws SQLException;
}
