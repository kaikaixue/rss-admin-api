package com.ruoyi.web.controller.system.app;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.AppLoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysSubscriptions;
import com.ruoyi.system.service.ISysSubscriptionsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.ruoyi.common.core.domain.AjaxResult.success;

@RestController
@RequestMapping("/app/subscription")
public class SysAppSubscriptionController {

    @Resource
    private ISysSubscriptionsService sysSubscriptionsService;

    @PostMapping("/add")
    public AjaxResult addSubscription(@RequestParam Long feedId) {
        AppLoginUser appLoginUser = SecurityUtils.getAppLoginUser();
        SysSubscriptions sysSubscriptions = new SysSubscriptions();
        sysSubscriptions.setUserId(appLoginUser.getUserId());
        sysSubscriptions.setFeedId(feedId);
        return success(sysSubscriptionsService.save(sysSubscriptions));
    }


}
