package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.PortalTheme2;
import com.ruoyi.common.core.domain.Ztree;

import java.util.List;

/**
 * 门户主题Service接口
 * 
 * @author ruoyi
 * @date 2019-12-20
 */
public interface IPortalThemeService2 
{
    /**
     * 查询门户主题
     * 
     * @param id 门户主题ID
     * @return 门户主题
     */
    public PortalTheme2 selectPortalThemeById(Long id);

    /**
     * 查询门户主题列表
     * 
     * @param portalTheme 门户主题
     * @return 门户主题集合
     */
    public List<PortalTheme2> selectPortalThemeList(PortalTheme2 portalTheme);

    /**
     * 新增门户主题
     * 
     * @param portalTheme 门户主题
     * @return 结果
     */
    public int insertPortalTheme(PortalTheme2 portalTheme);

    /**
     * 修改门户主题
     * 
     * @param portalTheme 门户主题
     * @return 结果
     */
    public int updatePortalTheme(PortalTheme2 portalTheme);

    /**
     * 批量删除门户主题
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePortalThemeByIds(String ids);

    /**
     * 删除门户主题信息
     * 
     * @param id 门户主题ID
     * @return 结果
     */
    public int deletePortalThemeById(Long id);

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

	public List<Ztree> selectCategoryTree();
}
