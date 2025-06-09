package com.ggzed.im.model.req;

import lombok.Data;
import javax.validation.constraints.*;
/**
 * @description 数据源管理请求参数
 * @param
 * @author  lkj
 * @date  2025/6/9
 * @return
 */

@Data
public class RepositoryConfigReq {
    @NotBlank(message = "资源库名称不能为空")
    @Size(max = 100, message = "资源库名称长度不能超过100个字符")
    private String repoName;

    @NotBlank(message = "资源库类型不能为空")
    @Pattern(regexp = "MySql|Oracle|PostgreSQL|SQL Server",
             message = "资源库类型必须是MySql、Oracle、PostgreSQL或SQL Server")
    private String repoType;

    @NotBlank(message = "访问模式不能为空")
    @Pattern(regexp = "Native|JNDI|ODBC", 
             message = "访问模式必须是Native、JNDI或ODBC")
    private String accessMode;

    @NotBlank(message = "主机地址不能为空")
    @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$|^localhost$|^[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9](?:\\.[a-zA-Z]{2,})+$", 
             message = "主机地址必须是有效的IP地址或域名")
    private String host;

    @NotNull(message = "端口号不能为空")
    @Min(value = 1, message = "端口号最小值为1")
    @Max(value = 65535, message = "端口号最大值为65535")
    private Integer port;

    @NotBlank(message = "数据库名称不能为空")
    @Size(max = 100, message = "数据库名称长度不能超过100个字符")
    private String databaseName;

    @Size(max = 500, message = "连接参数长度不能超过500个字符")
    private String connectionParams;

    @NotBlank(message = "数据库账号不能为空")
    @Size(max = 50, message = "数据库账号长度不能超过50个字符")
    private String username;

    @NotBlank(message = "数据库密码不能为空")
    @Size(min = 8, max = 50, message = "密码长度必须在8到50个字符之间")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", 
             message = "密码必须包含大小写字母和数字")
    private String password;
}