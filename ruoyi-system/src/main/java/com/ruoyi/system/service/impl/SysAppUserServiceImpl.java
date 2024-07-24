package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysAppUserMapper;
import com.ruoyi.system.domain.SysAppUser;
import com.ruoyi.system.service.ISysAppUserService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
@Service
public class SysAppUserServiceImpl implements ISysAppUserService 
{
    @Autowired
    private SysAppUserMapper sysAppUserMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysAppUser selectSysAppUserByUserId(Long userId)
    {
        return sysAppUserMapper.selectSysAppUserByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysAppUser 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysAppUser> selectSysAppUserList(SysAppUser sysAppUser)
    {
        return sysAppUserMapper.selectSysAppUserList(sysAppUser);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysAppUser 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysAppUser(SysAppUser sysAppUser)
    {
        return sysAppUserMapper.insertSysAppUser(sysAppUser);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysAppUser 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysAppUser(SysAppUser sysAppUser)
    {
        return sysAppUserMapper.updateSysAppUser(sysAppUser);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysAppUserByUserIds(Long[] userIds)
    {
        return sysAppUserMapper.deleteSysAppUserByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysAppUserByUserId(Long userId)
    {
        return sysAppUserMapper.deleteSysAppUserByUserId(userId);
    }
}
