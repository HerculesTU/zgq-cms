package com.ruoyi.cms.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cms.domain.PortalTheme;
import com.ruoyi.cms.service.IPortalThemeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门户主题Controller
 * 
 * @author ruoyi
 * @date 2019-12-20
 */
@Controller
@RequestMapping("/cms/portalTheme")
public class PortalThemeController extends BaseController
{
    private String prefix = "cms/portalTheme";

    @Autowired
    private IPortalThemeService portalThemeService;

    @RequiresPermissions("cms:portalTheme:view")
    @GetMapping()
    public String portalTheme()
    {
        return prefix + "/portalTheme";
    }

    /**
     * 查询门户主题列表
     */
    @RequiresPermissions("cms:portalTheme:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PortalTheme portalTheme)
    {
        startPage();
        List<PortalTheme> list = portalThemeService.selectPortalThemeList(portalTheme);
        return getDataTable(list);
    }

    /**
     * 导出门户主题列表
     */
    @RequiresPermissions("cms:portalTheme:export")
    @Log(title = "门户主题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PortalTheme portalTheme)
    {
        List<PortalTheme> list = portalThemeService.selectPortalThemeList(portalTheme);
        ExcelUtil<PortalTheme> util = new ExcelUtil<PortalTheme>(PortalTheme.class);
        return util.exportExcel(list, "theme");
    }

    /**
     * 新增门户主题
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存门户主题
     */
    @RequiresPermissions("cms:portalTheme:add")
    @Log(title = "门户主题", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PortalTheme portalTheme)
    {
        return toAjax(portalThemeService.insertPortalTheme(portalTheme));
    }

    /**
     * 修改门户主题
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PortalTheme portalTheme = portalThemeService.selectPortalThemeById(id);
        mmap.put("portalTheme", portalTheme);
        return prefix + "/edit";
    }

    /**
     * 修改保存门户主题
     */
    @RequiresPermissions("cms:portalTheme:edit")
    @Log(title = "门户主题", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PortalTheme portalTheme)
    {
        return toAjax(portalThemeService.updatePortalTheme(portalTheme));
    }

    /**
     * 删除门户主题
     */
    @RequiresPermissions("cms:portalTheme:remove")
    @Log(title = "门户主题", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(portalThemeService.deletePortalThemeByIds(ids));
    }

    @PostMapping( "/updatePortalThemeConfig")
    @ResponseBody
    public AjaxResult updatePortalThemeConfig(String code){
        portalThemeService.updatePortalThemeConfig(code);
        return success();
    }
}
