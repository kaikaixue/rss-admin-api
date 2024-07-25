package com.ruoyi.web.controller.system.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.dto.RegisterDTO;
import com.ruoyi.system.service.IAppAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/auth")
public class AuthController extends BaseController {

    @Resource
    IAppAuthService appAuthService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterDTO registerDTO) {

        return toAjax(appAuthService.register(registerDTO));
    }
}
