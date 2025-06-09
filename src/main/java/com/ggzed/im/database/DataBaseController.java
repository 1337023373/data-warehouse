package com.ggzed.im.database;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author lkj
 * @Date 2025/6/6 15:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/table")
@Api(tags = "数据库管理")
public class DataBaseController {
    @Resource
    private DataSource dataSource;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/name")
    @ApiOperation("获取数据表名称")
    public List<String> getTableName(@RequestParam String dbName) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        ResultSet resultSet = connection.getMetaData().getTables(dbName, null, "%", new String[]{"TABLE"});
        while (resultSet.next()) {
            list.add(resultSet.getString("TABLE_NAME"));
        }
        return list;
    }

    @GetMapping("/columns")
    @ApiOperation("获取表字段信息")
    public List<Map<String, String>> getColumnName(@RequestParam String tableName,@RequestParam String dbName) throws SQLException {
        List<Map<String, String>> result = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        ResultSet resultSet = connection.getMetaData().getColumns(dbName, null, tableName, null);
        while (resultSet.next()) {
            Map<String, String> map = new HashMap<>();
            map.put("COLUMN_NAME", resultSet.getString("COLUMN_NAME"));
            map.put("DATA_TYPE", resultSet.getString("DATA_TYPE"));
            result.add(map);
        }
        return result;
    }

    @GetMapping("/data")
    @ApiOperation("获取表信息")
    public List<Map<String, Object>> getData(@RequestParam String tableName,@RequestParam String dbName) throws SQLException {
        String sql = "select * from " + tableName;
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/databases")
    @ApiOperation("获取所有数据库名称")
    public List<String> getAllDatabases() throws SQLException {
        List<String> dbList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            ResultSet rs = connection.getMetaData().getCatalogs();
            while (rs.next()) {
                dbList.add(rs.getString("TABLE_CAT"));
            }
        }
        return dbList;
    }

}
