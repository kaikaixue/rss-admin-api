package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysFeed;

/**
 * 订阅源Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
public interface SysFeedMapper 
{
    /**
     * 查询订阅源
     * 
     * @param feedId 订阅源主键
     * @return 订阅源
     */
    public SysFeed selectSysFeedByFeedId(Long feedId);

    /**
     * 查询订阅源列表
     * 
     * @param sysFeed 订阅源
     * @return 订阅源集合
     */
    public List<SysFeed> selectSysFeedList(SysFeed sysFeed);

    /**
     * 新增订阅源
     * 
     * @param sysFeed 订阅源
     * @return 结果
     */
    public int insertSysFeed(SysFeed sysFeed);

    /**
     * 修改订阅源
     * 
     * @param sysFeed 订阅源
     * @return 结果
     */
    public int updateSysFeed(SysFeed sysFeed);

    /**
     * 删除订阅源
     * 
     * @param feedId 订阅源主键
     * @return 结果
     */
    public int deleteSysFeedByFeedId(Long feedId);

    /**
     * 批量删除订阅源
     * 
     * @param feedIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysFeedByFeedIds(Long[] feedIds);
}
