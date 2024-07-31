package com.ruoyi.web.controller.system.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysFeed;
import com.ruoyi.system.service.ISysFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/app/feed")
public class SysAppFeedController extends BaseController {

    @Autowired
    private ISysFeedService sysFeedService;

    @GetMapping(value = "/analysis")
    public AjaxResult analysisByUrl(@RequestParam String url) {
        return success(sysFeedService.analysisByUrl(url));
    }

    @PostMapping("/listPage")
    public AjaxResult listSubscription() {
        startPage();
        List<SysFeed> list = sysFeedService.listByPageSubscriptions();
        return success(getDataTable(list));
    }

    @GetMapping(value = "/hello")
    public AjaxResult hello() {
        return success("hello");
    }
}
