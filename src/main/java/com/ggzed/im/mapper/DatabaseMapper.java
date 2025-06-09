package com.ggzed.im.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggzed.im.model.entity.RepositoryConfig;
import net.sf.jsqlparser.schema.Database;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lkj
 * @Date 2025/6/9 10:38
 * @Version 1.0
 */
@Mapper
public interface DatabaseMapper extends BaseMapper<RepositoryConfig> {
}
