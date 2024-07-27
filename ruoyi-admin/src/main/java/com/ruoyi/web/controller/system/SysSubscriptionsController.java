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
import com.ruoyi.system.domain.SysSubscriptions;
import com.ruoyi.system.service.ISysSubscriptionsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订阅关系Controller
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
@RestController
@RequestMapping("/system/subscriptions")
public class SysSubscriptionsController extends BaseController
{
    @Autowired
    private ISysSubscriptionsService sysSubscriptionsService;

    /**
     * 查询订阅关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysSubscriptions sysSubscriptions)
    {
        startPage();
        List<SysSubscriptions> list = sysSubscriptionsService.selectSysSubscriptionsList(sysSubscriptions);
        return getDataTable(list);
    }

    /**
     * 导出订阅关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:export')")
    @Log(title = "订阅关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSubscriptions sysSubscriptions)
    {
        List<SysSubscriptions> list = sysSubscriptionsService.selectSysSubscriptionsList(sysSubscriptions);
        ExcelUtil<SysSubscriptions> util = new ExcelUtil<SysSubscriptions>(SysSubscriptions.class);
        util.exportExcel(response, list, "订阅关系数据");
    }

    /**
     * 获取订阅关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:query')")
    @GetMapping(value = "/{subscriptionId}")
    public AjaxResult getInfo(@PathVariable("subscriptionId") Long subscriptionId)
    {
        return success(sysSubscriptionsService.selectSysSubscriptionsBySubscriptionId(subscriptionId));
    }

    /**
     * 新增订阅关系
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:add')")
    @Log(title = "订阅关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSubscriptions sysSubscriptions)
    {
        return toAjax(sysSubscriptionsService.insertSysSubscriptions(sysSubscriptions));
    }

    /**
     * 修改订阅关系
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:edit')")
    @Log(title = "订阅关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSubscriptions sysSubscriptions)
    {
        return toAjax(sysSubscriptionsService.updateSysSubscriptions(sysSubscriptions));
    }

    /**
     * 删除订阅关系
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:remove')")
    @Log(title = "订阅关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{subscriptionIds}")
    public AjaxResult remove(@PathVariable Long[] subscriptionIds)
    {
        return toAjax(sysSubscriptionsService.deleteSysSubscriptionsBySubscriptionIds(subscriptionIds));
    }
}
