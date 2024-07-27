package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysFeedMapper;
import com.ruoyi.system.domain.SysFeed;
import com.ruoyi.system.service.ISysFeedService;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 订阅源Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
@Service
public class SysFeedServiceImpl implements ISysFeedService 
{
    @Resource
    private SysFeedMapper sysFeedMapper;

    @Override
    public SysFeed analysisByUrl(String url) {
        SysFeed sysFeed = new SysFeed();
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
            // 关闭输入流
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysFeed;
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
