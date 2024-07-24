package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysAppUser;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
public interface SysAppUserMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysAppUser selectSysAppUserByUserId(Long userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysAppUser 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysAppUser> selectSysAppUserList(SysAppUser sysAppUser);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysAppUser 【请填写功能名称】
     * @return 结果
     */
    public int insertSysAppUser(SysAppUser sysAppUser);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysAppUser 【请填写功能名称】
     * @return 结果
     */
    public int updateSysAppUser(SysAppUser sysAppUser);

    /**
     * 删除【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysAppUserByUserId(Long userId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysAppUserByUserIds(Long[] userIds);
}
