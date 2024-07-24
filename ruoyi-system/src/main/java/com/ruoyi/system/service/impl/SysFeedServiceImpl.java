package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysFeedMapper;
import com.ruoyi.system.domain.SysFeed;
import com.ruoyi.system.service.ISysFeedService;

/**
 * 订阅源Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
@Service
public class SysFeedServiceImpl implements ISysFeedService 
{
    @Autowired
    private SysFeedMapper sysFeedMapper;

    /**
     * 查询订阅源
     * 
     * @param feedId 订阅源主键
     * @return 订阅源
     */
    @Override
    public SysFeed selectSysFeedByFeedId(Long feedId)
    {
        return sysFeedMapper.selectSysFeedByFeedId(feedId);
    }

    /**
     * 查询订阅源列表
     * 
     * @param sysFeed 订阅源
     * @return 订阅源
     */
    @Override
    public List<SysFeed> selectSysFeedList(SysFeed sysFeed)
    {
        return sysFeedMapper.selectSysFeedList(sysFeed);
    }

    /**
     * 新增订阅源
     * 
     * @param sysFeed 订阅源
     * @return 结果
     */
    @Override
    public int insertSysFeed(SysFeed sysFeed)
    {
        return sysFeedMapper.insertSysFeed(sysFeed);
    }

    /**
     * 修改订阅源
     * 
     * @param sysFeed 订阅源
     * @return 结果
     */
    @Override
    public int updateSysFeed(SysFeed sysFeed)
    {
        return sysFeedMapper.updateSysFeed(sysFeed);
    }

    /**
     * 批量删除订阅源
     * 
     * @param feedIds 需要删除的订阅源主键
     * @return 结果
     */
    @Override
    public int deleteSysFeedByFeedIds(Long[] feedIds)
    {
        return sysFeedMapper.deleteSysFeedByFeedIds(feedIds);
    }

    /**
     * 删除订阅源信息
     * 
     * @param feedId 订阅源主键
     * @return 结果
     */
    @Override
    public int deleteSysFeedByFeedId(Long feedId)
    {
        return sysFeedMapper.deleteSysFeedByFeedId(feedId);
    }
}
