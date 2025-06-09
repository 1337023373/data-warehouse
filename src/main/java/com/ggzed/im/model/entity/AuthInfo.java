package com.ggzed.im.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.ggzed.im.model.common.BaseModel;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限信息
 * </p>
 *
 * @author ggzed
 * @since 2024-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@TableName("auth_info")
public class AuthInfo extends BaseModel {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
