package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysSubscriptions;

/**
 * 订阅关系Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
public interface SysSubscriptionsMapper 
{
    /**
     * 查询订阅关系
     * 
     * @param subscriptionId 订阅关系主键
     * @return 订阅关系
     */
    public SysSubscriptions selectSysSubscriptionsBySubscriptionId(Long subscriptionId);

    /**
     * 查询订阅关系列表
     * 
     * @param sysSubscriptions 订阅关系
     * @return 订阅关系集合
     */
    public List<SysSubscriptions> selectSysSubscriptionsList(SysSubscriptions sysSubscriptions);

    /**
     * 新增订阅关系
     * 
     * @param sysSubscriptions 订阅关系
     * @return 结果
     */
    public int insertSysSubscriptions(SysSubscriptions sysSubscriptions);

    /**
     * 修改订阅关系
     * 
     * @param sysSubscriptions 订阅关系
     * @return 结果
     */
    public int updateSysSubscriptions(SysSubscriptions sysSubscriptions);

    /**
     * 删除订阅关系
     * 
     * @param subscriptionId 订阅关系主键
     * @return 结果
     */
    public int deleteSysSubscriptionsBySubscriptionId(Long subscriptionId);

    /**
     * 批量删除订阅关系
     * 
     * @param subscriptionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysSubscriptionsBySubscriptionIds(Long[] subscriptionIds);
}
