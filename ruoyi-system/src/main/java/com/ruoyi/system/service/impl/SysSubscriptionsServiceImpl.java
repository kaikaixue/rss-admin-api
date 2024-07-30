package com.ruoyi.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.ruoyi.system.domain.SysFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysSubscriptionsMapper;
import com.ruoyi.system.domain.SysSubscriptions;
import com.ruoyi.system.service.ISysSubscriptionsService;

import javax.annotation.Resource;

/**
 * 订阅关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
@Service
public class SysSubscriptionsServiceImpl extends ServiceImpl<SysSubscriptionsMapper, SysSubscriptions> implements ISysSubscriptionsService
{
    @Resource
    private SysSubscriptionsMapper sysSubscriptionsMapper;

    /**
     * 查询订阅关系
     * 
     * @param subscriptionId 订阅关系主键
     * @return 订阅关系
     */
    @Override
    public SysSubscriptions selectSysSubscriptionsBySubscriptionId(Long subscriptionId)
    {
        return sysSubscriptionsMapper.selectSysSubscriptionsBySubscriptionId(subscriptionId);
    }

    /**
     * 查询订阅关系列表
     * 
     * @param sysSubscriptions 订阅关系
     * @return 订阅关系
     */
    @Override
    public List<SysSubscriptions> selectSysSubscriptionsList(SysSubscriptions sysSubscriptions)
    {
        return sysSubscriptionsMapper.selectSysSubscriptionsList(sysSubscriptions);
    }

    /**
     * 新增订阅关系
     * 
     * @param sysSubscriptions 订阅关系
     * @return 结果
     */
    @Override
    public int insertSysSubscriptions(SysSubscriptions sysSubscriptions)
    {
        return sysSubscriptionsMapper.insertSysSubscriptions(sysSubscriptions);
    }

    /**
     * 修改订阅关系
     * 
     * @param sysSubscriptions 订阅关系
     * @return 结果
     */
    @Override
    public int updateSysSubscriptions(SysSubscriptions sysSubscriptions)
    {
        return sysSubscriptionsMapper.updateSysSubscriptions(sysSubscriptions);
    }

    /**
     * 批量删除订阅关系
     * 
     * @param subscriptionIds 需要删除的订阅关系主键
     * @return 结果
     */
    @Override
    public int deleteSysSubscriptionsBySubscriptionIds(Long[] subscriptionIds)
    {
        return sysSubscriptionsMapper.deleteSysSubscriptionsBySubscriptionIds(subscriptionIds);
    }

    /**
     * 删除订阅关系信息
     * 
     * @param subscriptionId 订阅关系主键
     * @return 结果
     */
    @Override
    public int deleteSysSubscriptionsBySubscriptionId(Long subscriptionId)
    {
        return sysSubscriptionsMapper.deleteSysSubscriptionsBySubscriptionId(subscriptionId);
    }
}
