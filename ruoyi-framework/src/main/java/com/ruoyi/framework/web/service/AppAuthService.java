package com.ruoyi.framework.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.AppLoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.UserNotExistsException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.security.AppAuthenticationManager;
import com.ruoyi.framework.security.context.AuthenticationContextHolder;
import com.ruoyi.common.core.domain.entity.SysAppUser;
import com.ruoyi.system.domain.dto.RegisterDTO;
import com.ruoyi.system.mapper.SysAppUserMapper;
import com.ruoyi.system.service.IAppAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AppAuthService extends ServiceImpl<SysAppUserMapper, SysAppUser> {

    @Resource
    SysAppUserMapper appUserMapper;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private AppAuthenticationManager appAuthenticationManager;

    @Resource
    private AppTokenService appTokenService;

    @Transactional
    public int register(RegisterDTO registerDTO) {
        SysAppUser sysAppUser = SysAppUser.builder()
                .username(registerDTO.getUsername())
                .passwordHash(SecurityUtils.encryptPassword(registerDTO.getPassword()))
                .build();
        return save(sysAppUser) ? 1 : 0;
    }

    public boolean checkUsernameUnique(String username) {
        QueryWrapper<SysAppUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        SysAppUser user = appUserMapper.selectOne(queryWrapper);
        return !StringUtils.isNotNull(user);
    }

    public String login(String username, String password) {
        // 登录前置校验
        loginPreCheck(username, password);
        // 用户认证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = appAuthenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        AppLoginUser appLoginUser = (AppLoginUser) authentication.getPrincipal();
        return appTokenService.createToken(appLoginUser);
    }

    public void loginPreCheck(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
    }
}
