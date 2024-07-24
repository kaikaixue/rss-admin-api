package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysFeed;
import com.ruoyi.system.service.ISysFeedService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订阅源Controller
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
@RestController
@RequestMapping("/system/feed")
public class SysFeedController extends BaseController
{
    @Autowired
    private ISysFeedService sysFeedService;

    /**
     * 查询订阅源列表
     */
    @PreAuthorize("@ss.hasPermi('system:feed:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFeed sysFeed)
    {
        startPage();
        List<SysFeed> list = sysFeedService.selectSysFeedList(sysFeed);
        return getDataTable(list);
    }

    /**
     * 导出订阅源列表
     */
    @PreAuthorize("@ss.hasPermi('system:feed:export')")
    @Log(title = "订阅源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFeed sysFeed)
    {
        List<SysFeed> list = sysFeedService.selectSysFeedList(sysFeed);
        ExcelUtil<SysFeed> util = new ExcelUtil<SysFeed>(SysFeed.class);
        util.exportExcel(response, list, "订阅源数据");
    }

    /**
     * 获取订阅源详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:feed:query')")
    @GetMapping(value = "/{feedId}")
    public AjaxResult getInfo(@PathVariable("feedId") Long feedId)
    {
        return success(sysFeedService.selectSysFeedByFeedId(feedId));
    }

    /**
     * 新增订阅源
     */
    @PreAuthorize("@ss.hasPermi('system:feed:add')")
    @Log(title = "订阅源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFeed sysFeed)
    {
        return toAjax(sysFeedService.insertSysFeed(sysFeed));
    }

    /**
     * 修改订阅源
     */
    @PreAuthorize("@ss.hasPermi('system:feed:edit')")
    @Log(title = "订阅源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFeed sysFeed)
    {
        return toAjax(sysFeedService.updateSysFeed(sysFeed));
    }

    /**
     * 删除订阅源
     */
    @PreAuthorize("@ss.hasPermi('system:feed:remove')")
    @Log(title = "订阅源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feedIds}")
    public AjaxResult remove(@PathVariable Long[] feedIds)
    {
        return toAjax(sysFeedService.deleteSysFeedByFeedIds(feedIds));
    }
}
