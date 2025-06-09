package com.ggzed.im.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ggzed.im.model.common.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源库配置实体类
 * @author lkj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("repository_config")
public class RepositoryConfig extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 资源库名称
     */
    private String repoName;

    /**
     * 资源库类型
     */
    private String repoType;

    /**
     * 访问模式
     */
    private String accessMode;

    /**
     * 数据库主机名/IP地址
     */
    private String host;

    /**
     * 数据库端口号
     */
    private Integer port;

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 连接参数
     * 格式示例：characterEncoding=utf8#autoReconnect=true#useSSL=false
     */
    private String connectionParams;

    /**
     * 数据库登录账号
     */
    private String username;

    /**
     * 数据库登录密码
     */
    private String password;

    /**
     * 连接状态(0-未连接,1-已连接)
     */
    private Integer status;

    /**
     * 最后测试连接时间
     */
    @TableField("last_test_time")
    private Date lastTestTime;

    /**
     * 获取完整的JDBC连接URL
     */
    public String getJdbcUrl() {
        String baseUrl = String.format("jdbc:mysql://%s:%d/%s", host, port, databaseName);
        if (StringUtils.isNotBlank(connectionParams)) {
            String params = connectionParams.replace("#", "&");
            return baseUrl + "?" + params;
        }
        return baseUrl;
    }

    /**
     * 获取简化的连接信息（用于日志等不敏感场景）
     */
    public String getSimpleConnectionInfo() {
        return String.format("%s@%s:%d/%s", username, host, port, databaseName);
    }
}