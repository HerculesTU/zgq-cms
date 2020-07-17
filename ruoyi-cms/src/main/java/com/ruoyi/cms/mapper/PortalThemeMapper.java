package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.PortalTheme;
import java.util.List;

/**
 * 门户主题Mapper接口
 * 
 * @author ruoyi
 * @date 2019-12-20
 */
public interface PortalThemeMapper 
{
    /**
     * 查询门户主题
     * 
     * @param id 门户主题ID
     * @return 门户主题
     */
    public PortalTheme selectPortalThemeById(Long id);

    /**
     * 查询门户主题列表
     * 
     * @param portalTheme 门户主题
     * @return 门户主题集合
     */
    public List<PortalTheme> selectPortalThemeList(PortalTheme portalTheme);

    /**
     * 新增门户主题
     * 
     * @param portalTheme 门户主题
     * @return 结果
     */
    public int insertPortalTheme(PortalTheme portalTheme);

    /**
     * 修改门户主题
     * 
     * @param portalTheme 门户主题
     * @return 结果
     */
    public int updatePortalTheme(PortalTheme portalTheme);

    /**
     * 删除门户主题
     * 
     * @param id 门户主题ID
     * @return 结果
     */
    public int deletePortalThemeById(Long id);

    /**
     * 批量删除门户主题
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePortalThemeByIds(String[] ids);

    /**
     * 更新门户主题配置
     * @param theme
     * @return
     */
    public int updatePortalThemeConfig(String theme);

    /**
     * 查询当前门户主题
     * @return
     */
    public String queryCurrentPortalTheme();
}
