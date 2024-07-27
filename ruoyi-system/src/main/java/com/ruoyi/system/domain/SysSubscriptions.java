package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订阅关系对象 sys_subscriptions
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
public class SysSubscriptions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订阅id */
    private Long subscriptionId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 订阅源id */
    @Excel(name = "订阅源id")
    private Long feedId;

    /** 订阅创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订阅创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 订阅更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订阅更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setSubscriptionId(Long subscriptionId) 
    {
        this.subscriptionId = subscriptionId;
    }

    public Long getSubscriptionId() 
    {
        return subscriptionId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setFeedId(Long feedId) 
    {
        this.feedId = feedId;
    }

    public Long getFeedId() 
    {
        return feedId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("subscriptionId", getSubscriptionId())
            .append("userId", getUserId())
            .append("feedId", getFeedId())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
