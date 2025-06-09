package com.ggzed.im.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ggzed.im.mapper.DatabaseMapper;
import com.ggzed.im.model.entity.RepositoryConfig;
import org.springframework.stereotype.Repository;

/**
 * @Author lkj
 * @Date 2025/6/9 10:37
 * @Version 1.0
 */
@Repository
public class DatabaseRepository extends ServiceImpl<DatabaseMapper, RepositoryConfig> {
}
