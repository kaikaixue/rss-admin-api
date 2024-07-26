package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.entity.SysAppUser;
import com.ruoyi.system.domain.dto.RegisterDTO;

public interface IAppAuthService extends IService<SysAppUser> {
    int register(RegisterDTO registerDTO);

    boolean checkUsernameUnique(String username);

    String login(String username, String password);
}
