package com.ggzed.im.service;

import com.ggzed.im.model.req.auth.LoginReq;
import com.ggzed.im.model.req.auth.RegisterReq;
import com.ggzed.im.model.vo.LoginVo;
import com.ggzed.im.model.vo.MenuVo;

import java.util.List;

public interface AuthService {

    /**
     * 登录
     */
    LoginVo login(LoginReq loginReq);

    void register(RegisterReq req);

    List<MenuVo> menus();
}
