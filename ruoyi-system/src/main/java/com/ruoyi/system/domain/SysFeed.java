package com.ruoyi.system.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订阅源对象 sys_feed
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
@TableName("sys_feed")
public class SysFeed implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 订阅源ID（主键） */
    @TableId
    private Long feedId;

    @Excel(name = "订阅源链接")
    private String feedLink;

    /** 订阅源URL */
    @Excel(name = "订阅源URL")
    private String feedUrl;

    /** 订阅源标题 */
    @Excel(name = "订阅源标题")
    private String feedTitle;

    /** 订阅源描述 */
    @Excel(name = "订阅源描述")
    private String feedDescription;

    /** 订阅源Logo */
    @Excel(name = "订阅源Logo")
    private String feedLogo;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.UPDATE)
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    /** 是否为推荐订阅源 */
    @Excel(name = "是否为推荐订阅源")
    private Integer isRecommended;

    public void setFeedId(Long feedId) 
    {
        this.feedId = feedId;
    }

    public Long getFeedId() 
    {
        return feedId;
    }
    public void setFeedUrl(String feedUrl) 
    {
        this.feedUrl = feedUrl;
    }

    public String getFeedUrl() 
    {
        return feedUrl;
    }

    public void setFeedLink(String feedLink)
    {
        this.feedLink = feedLink;
    }

    public String getFeedLink()
    {
        return feedLink;
    }
    public void setFeedTitle(String feedTitle) 
    {
        this.feedTitle = feedTitle;
    }

    public String getFeedTitle() 
    {
        return feedTitle;
    }
    public void setFeedDescription(String feedDescription) 
    {
        this.feedDescription = feedDescription;
    }

    public String getFeedDescription() 
    {
        return feedDescription;
    }
    public void setFeedLogo(String feedLogo) 
    {
        this.feedLogo = feedLogo;
    }

    public String getFeedLogo() 
    {
        return feedLogo;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }
    public void setIsRecommended(Integer isRecommended) 
    {
        this.isRecommended = isRecommended;
    }

    public Integer getIsRecommended() 
    {
        return isRecommended;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("feedId", getFeedId())
            .append("feedUrl", getFeedUrl())
            .append("feedTitle", getFeedTitle())
            .append("feedDescription", getFeedDescription())
            .append("feedLogo", getFeedLogo())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("isRecommended", getIsRecommended())
            .toString();
    }
}
