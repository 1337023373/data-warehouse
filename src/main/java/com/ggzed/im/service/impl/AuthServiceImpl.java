package com.ggzed.im.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWT;
import com.ggzed.im.common.constant.JwtConstant;
import com.ggzed.im.common.exception.BizException;
import com.ggzed.im.common.result.ResultEnum;
import com.ggzed.im.model.entity.AuthInfo;
import com.ggzed.im.model.entity.Menus;
import com.ggzed.im.model.req.auth.LoginReq;
import com.ggzed.im.model.req.auth.RegisterReq;
import com.ggzed.im.model.vo.LoginVo;
import com.ggzed.im.model.vo.MenuVo;
import com.ggzed.im.repository.AuthInfoRepository;
import com.ggzed.im.repository.MenuRepository;
import com.ggzed.im.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private AuthInfoRepository authInfoRepository;
    @Resource
    private MenuRepository menuRepository;

    @Override
    public LoginVo login(LoginReq req) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        authenticationManager.authenticate(authenticationToken);
        //上一步没有抛出异常说明认证成功，我们向用户颁发jwt令牌
        return LoginVo
                .builder()
                .token(JWT.create()
                .setPayload("username", req.getUsername())
                .setKey(JwtConstant.JWT_SIGN_KEY.getBytes(StandardCharsets.UTF_8))
                .sign()).build();
    }

    @Override
    public void register(RegisterReq req) {
        AuthInfo exist = authInfoRepository.getByUsername(req.getUsername());
        if (Objects.nonNull(exist)) {
            throw new BizException(ResultEnum.USER_HAS_EXISTED);
        }
        String userId = IdUtil.simpleUUID();
        AuthInfo authInfo = AuthInfo.builder()
                .userId(userId)
                .username(req.getUsername())
                .password(req.getPassword()).build();
        authInfoRepository.save(authInfo);
    }

    @Override
    public List<MenuVo> menus() {
        List<Menus> menus = menuRepository.list();

        return menus.stream().filter(m -> m.getParentId() == 0).map(m -> {
            MenuVo menuVo = Convert.convert(MenuVo.class, m);
            menuVo.setChildren(getChildrenMenus(menus, m.getId()));
            return menuVo;
        }).collect(Collectors.toList());
    }

    /**
     * 递归查询子菜单
     *
     * @param menus    菜单
     * @param parentId 父id
     * @return 子菜单
     */
    private List<MenuVo> getChildrenMenus(List<Menus> menus, Long parentId) {
        List<MenuVo> result = new ArrayList<>();
        for (Menus menu : menus) {
            if (Objects.equals(parentId, menu.getParentId())) {
                MenuVo vo = Convert.convert(MenuVo.class, menu);
                vo.setChildren(getChildrenMenus(menus,menu.getId()));
                result.add(vo);
            }
        }
        return result;
    }
}
