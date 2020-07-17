package com.ruoyi.cms.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cms.mapper.PortalThemeMapper;
import com.ruoyi.cms.domain.PortalTheme;
import com.ruoyi.cms.service.IPortalThemeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 门户主题Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-12-20
 */
@Service
public class PortalThemeServiceImpl implements IPortalThemeService 
{
    @Autowired
    private PortalThemeMapper portalThemeMapper;

    /**
     * 查询门户主题
     * 
     * @param id 门户主题ID
     * @return 门户主题
     */
    @Override
    public PortalTheme selectPortalThemeById(Long id)
    {
        return portalThemeMapper.selectPortalThemeById(id);
    }

    /**
     * 查询门户主题列表
     * 
     * @param portalTheme 门户主题
     * @return 门户主题
     */
    @Override
    public List<PortalTheme> selectPortalThemeList(PortalTheme portalTheme)
    {
        return portalThemeMapper.selectPortalThemeList(portalTheme);
    }

    /**
     * 新增门户主题
     * 
     * @param portalTheme 门户主题
     * @return 结果
     */
    @Override
    public int insertPortalTheme(PortalTheme portalTheme)
    {
        portalTheme.setCreateTime(DateUtils.getNowDate());
        return portalThemeMapper.insertPortalTheme(portalTheme);
    }

    /**
     * 修改门户主题
     * 
     * @param portalTheme 门户主题
     * @return 结果
     */
    @Override
    public int updatePortalTheme(PortalTheme portalTheme)
    {
        return portalThemeMapper.updatePortalTheme(portalTheme);
    }

    /**
     * 删除门户主题对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePortalThemeByIds(String ids)
    {
        return portalThemeMapper.deletePortalThemeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除门户主题信息
     * 
     * @param id 门户主题ID
     * @return 结果
     */
    @Override
    public int deletePortalThemeById(Long id)
    {
        return portalThemeMapper.deletePortalThemeById(id);
    }

    @Override
    public int updatePortalThemeConfig(String theme) {
        return portalThemeMapper.updatePortalThemeConfig(theme);
    }

    @Override
    public String queryCurrentPortalTheme() {
        return portalThemeMapper.queryCurrentPortalTheme();
    }
}
