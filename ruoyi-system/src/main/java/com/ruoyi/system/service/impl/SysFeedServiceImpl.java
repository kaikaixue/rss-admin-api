package com.ruoyi.system.service.impl;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.ruoyi.common.core.domain.model.AppLoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysSubscriptions;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysFeedMapper;
import com.ruoyi.system.domain.SysFeed;
import com.ruoyi.system.service.ISysFeedService;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 订阅源Service业务层处理
 * 
 * @author ruoyi
 *  2024-07-24
 */
@Service
public class SysFeedServiceImpl extends ServiceImpl<SysFeedMapper, SysFeed> implements ISysFeedService
{
    @Resource
    private SysFeedMapper sysFeedMapper;

    @Override
    public SysFeed analysisByUrl(String url) {
        QueryWrapper<SysFeed> sysFeedQueryWrapper = new QueryWrapper<>();
        sysFeedQueryWrapper.eq("feed_link", url);
        SysFeed oldFeed = sysFeedMapper.selectOne(sysFeedQueryWrapper);
        if (oldFeed != null) {
            return oldFeed;
        }
        SysFeed sysFeed = new SysFeed();
        SysFeed newFeed = new SysFeed();
        try {
            // 创建URL对象
            URL rssUrl = new URL(url);
            // 打开Url连接
            URLConnection connection = rssUrl.openConnection();
            // 获取输入流
            InputStream inputStream = connection.getInputStream();
            // 创建XML解析器
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 解析XML文件
            Document document = builder.parse(inputStream);
            // 获取channel元素
            Element channel = (Element) document.getElementsByTagName("channel").item(0);
            // 获取并打印channel的标题、链接和描述
            String title = channel.getElementsByTagName("title").item(0).getTextContent();
            String link = channel.getElementsByTagName("link").item(0).getTextContent();
            String description = channel.getElementsByTagName("description").item(0).getTextContent();
            Element image = (Element) channel.getElementsByTagName("image").item(0);
            if (image != null) {
                // 获取image元素中的url标签值
                String imageUrl = image.getElementsByTagName("url").item(0).getTextContent();
                sysFeed.setFeedLogo(imageUrl);
            }
            sysFeed.setFeedTitle(title);
            sysFeed.setFeedUrl(link);
            sysFeed.setFeedDescription(description);
            sysFeed.setFeedLink(url);
            // 关闭输入流
            inputStream.close();
            save(sysFeed);
            newFeed = selectSysFeedByFeedId(sysFeed.getFeedId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFeed;
    }

    @Override
    public List<SysFeed> listByPageSubscriptions(Integer page, Integer pageSize) {
        AppLoginUser appLoginUser = SecurityUtils.getAppLoginUser();
        Page<SysFeed> rowPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<SysFeed> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(SysSubscriptions::getUserId, appLoginUser.getUserId())
        return null;
    }


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
