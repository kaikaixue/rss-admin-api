package com.ruoyi.common.core.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 sys_app_user
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
@Data
@Builder
public class SysAppUser
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId
    private Long userId;

    /** $column.columnComment */
    @Excel(name = "用户名", readConverterExp = "$column.readConverterExp()")
    private String username;

    /** $column.columnComment */
    @Excel(name = "电子邮件", readConverterExp = "$column.readConverterExp()")
    private String email;

    /** $column.columnComment */
    @Excel(name = "头像", readConverterExp = "$column.readConverterExp()")
    private String avatar;

    /** $column.columnComment */
    @Excel(name = "手机号", readConverterExp = "$column.readConverterExp()")
    private String phone;

    /** $column.columnComment */
    @Excel(name = "密码", readConverterExp = "$column.readConverterExp()")
    private String passwordHash;

    /** $column.columnComment */
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建时间", readConverterExp = "$column.readConverterExp()")
    private Date createdAt;

    /** $column.columnComment */
    @TableField(fill = FieldFill.UPDATE)
    @Excel(name = "更新时间", readConverterExp = "$column.readConverterExp()")
    private Date updatedAt;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setPasswordHash(String passwordHash) 
    {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() 
    {
        return passwordHash;
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
            .append("userId", getUserId())
            .append("username", getUsername())
            .append("email", getEmail())
            .append("avatar", getAvatar())
            .append("phone", getPhone())
            .append("passwordHash", getPasswordHash())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
