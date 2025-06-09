package com.ggzed.im.common.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author lkj
 * @Date 2025/6/9 10:18
 * @Version 1.0
 */
@Service
public class DatabaseConnectionStrategyFactory {
    private final Map<String, DatabaseConnectionStrategy> strategies;

    @Autowired
    public DatabaseConnectionStrategyFactory(
            List<DatabaseConnectionStrategy> strategyList) {
        strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        s -> s.getClass().getSimpleName().replace("ConnectionStrategy", ""),
                        Function.identity()
                ));
    }

    public DatabaseConnectionStrategy getStrategy(String dbType) {
        DatabaseConnectionStrategy strategy = strategies.get(dbType);
        if (strategy == null) {
            throw new IllegalArgumentException("不支持的数据库类型: " + dbType);
        }
        return strategy;
    }
}
