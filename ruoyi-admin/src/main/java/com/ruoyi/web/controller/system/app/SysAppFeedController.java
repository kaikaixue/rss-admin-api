package com.ruoyi.web.controller.system.app;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.ISysFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.ruoyi.common.core.domain.AjaxResult.success;

@RestController
@RequestMapping("/app/feed")
public class SysAppFeedController {

    @Autowired
    private ISysFeedService sysFeedService;

    @GetMapping(value = "/analysis")
    public AjaxResult analysisByUrl(@RequestParam String url) {
        return success(sysFeedService.analysisByUrl(url));
    }

    @GetMapping(value = "/hello")
    public AjaxResult hello() {
        return success("hello");
    }
}
