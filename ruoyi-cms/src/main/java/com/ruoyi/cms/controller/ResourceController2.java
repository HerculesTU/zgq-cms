package com.ruoyi.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.cms.domain.Attachment;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cms.domain.Resource;
import com.ruoyi.cms.service.IResourceService;
import com.ruoyi.cms.service.IResourceService2;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 资源Controller
 * 
 * @author wujiyue
 * @date 2019-11-23
 */
@Controller
@RequestMapping("/cms/resource2")
public class ResourceController2 extends BaseController
{
    private String prefix = "cms/resource2";

    @Autowired
    private IResourceService2 resourceService;

    @RequiresPermissions("cms:resource2:view")
    @GetMapping()
    public String resource2()
    {
        return prefix + "/resource2";
    }

    /**
     * 查询资源列表
     */
    @RequiresPermissions("cms:resource2:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Resource resource2)
    {
        startPage();
        List<Resource> list = resourceService.selectResourceList(resource2);
        return getDataTable(list);
    }

    /**
     * 导出资源列表
     */
    @RequiresPermissions("cms:resource2:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Resource resource2)
    {
        List<Resource> list = resourceService.selectResourceList(resource2);
        ExcelUtil<Resource> util = new ExcelUtil<Resource>(Resource.class);
        return util.exportExcel(list, "resource2");
    }

    /**
     * 新增资源
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存资源
     */
    @RequiresPermissions("cms:resource2:add")
    @Log(title = "资源", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Resource resource2)
    {
        return toAjax(resourceService.insertResource(resource2));
    }

    /**
     * 修改资源
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Resource resource2 = resourceService.selectResourceById(id);
        mmap.put("resource", resource2);
        return prefix + "/edit";
    }

    /**
     * 修改保存资源
     */
    @RequiresPermissions("cms:resource2:edit")
    @Log(title = "资源", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Resource resource2)
    {
        return toAjax(resourceService.updateResource(resource2));
    }

    /**
     * 删除资源
     */
    @RequiresPermissions("cms:resource2:remove")
    @Log(title = "资源", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(resourceService.deleteResourceByIds(ids));
    }


    /**
     * 上传资源请求
     */
    @PostMapping("/uploadResource")
    @ResponseBody
    public AjaxResult uploadResource(MultipartFile file) throws Exception
    {
        try
        {
            // 上传并返回新文件名称
            String path = FileUploadUtils.upload(Global.getResourcePath(), file);
            Map map=new HashMap();
            map.put("path",path);
            map.put("size",file.getSize());
            map.put("name",file.getOriginalFilename());
            return AjaxResult.success(map);
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }
}
