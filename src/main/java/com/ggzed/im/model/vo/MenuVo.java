package com.ggzed.im.model.vo;

import com.ggzed.im.model.entity.Menus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.util.List;

/**
 * @author: ggzed
 * @date: 2024/2/26 16:22
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVo extends Menus {
    private List<MenuVo> children;
}
