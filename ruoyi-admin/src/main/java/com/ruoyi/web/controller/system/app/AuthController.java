package com.ruoyi.web.controller.system.app;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.web.service.AppAuthService;
import com.ruoyi.system.domain.dto.LoginDTO;
import com.ruoyi.system.domain.dto.RegisterDTO;
import com.ruoyi.system.service.IAppAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Anonymous
@RestController
@RequestMapping("/app/auth")
public class AuthController extends BaseController {

    @Resource
    AppAuthService appAuthService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterDTO registerDTO) {
        if (registerDTO.getUsername() == null) {
            return error("用户名不能为空");
        }
        if (!appAuthService.checkUsernameUnique(registerDTO.getUsername())) {
            return error("用户名已存在");
        }
        return toAjax(appAuthService.register(registerDTO));
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginDTO loginDTO) {
        Map<String, Object> map;
        // 生成令牌
        map = appAuthService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return success(map);
    }
}
