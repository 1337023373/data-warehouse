package com.ggzed.im.database;

import com.ggzed.im.common.result.Result;
import com.ggzed.im.model.req.RepositoryConfigReq;
import com.ggzed.im.service.DatabaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jsqlparser.schema.Database;
import org.checkerframework.checker.units.qual.A;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.validation.Valid;
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
public class DatabaseController {
    @Resource
    private DataSource dataSource;
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private DatabaseService databaseService;

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

    @PostMapping("/saveDatabase")
    @ApiOperation("创建数据库连接")
    public Result<Boolean> saveDatabase(@RequestBody @Valid RepositoryConfigReq req) throws SQLException {
        Boolean b = databaseService.saveDatabase(req);
        return Result.success(b);
    }

    @PostMapping("/testDatabase")
    @ApiOperation("测试数据库连接")
    public Result<Boolean> testDatabase(@RequestBody @Valid RepositoryConfigReq req) throws SQLException {
        Boolean res = databaseService.testDatabase(req);
        return Result.success(res);
    }
}
