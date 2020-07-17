package com.ruoyi.oss.controller;

import com.alibaba.fastjson.JSON;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.util.ValidatorUtils;
import com.ruoyi.oss.domain.OssException;
import com.ruoyi.oss.domain.SysOss;
import com.ruoyi.oss.service.ISysOssService;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.oss.cloud.CloudConstant;
import com.ruoyi.oss.cloud.CloudConstant.CloudService;
import com.ruoyi.oss.cloud.CloudStorageConfig;
import com.ruoyi.oss.cloud.CloudStorageService;
import com.ruoyi.oss.cloud.OSSFactory;
import com.ruoyi.oss.cloud.valdator.AliyunGroup;
import com.ruoyi.oss.cloud.valdator.QcloudGroup;
import com.ruoyi.oss.cloud.valdator.QiniuGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 */
@Controller
@RequestMapping("system/oss")
public class SysOssController extends BaseController {
    private String prefix = "system/oss";

    private final static String KEY = CloudConstant.CLOUD_STORAGE_CONFIG_KEY;

    @Autowired
    private ISysOssService sysOssService;

    @Autowired
    private ISysConfigService sysConfigService;

    @RequiresPermissions("system:dept:view")
    @GetMapping()
    public String dept() {
        return prefix + "/oss";
    }

    /**
     * 列表
     */
    @RequestMapping("list")
    @RequiresPermissions("sys:oss:list")
    @ResponseBody
    public TableDataInfo list(SysOss sysOss) {
        startPage();
        List<SysOss> list = sysOssService.getList(sysOss);
        return getDataTable(list);
    }

    /**
     * 云存储配置信息
     */
    @RequestMapping("config")
    @RequiresPermissions("sys:oss:config")
    public String config(Model model) {
        String jsonconfig = sysConfigService.selectConfigByKey(CloudConstant.CLOUD_STORAGE_CONFIG_KEY);
        // 获取云存储配置信息
        CloudStorageConfig config = JSON.parseObject(jsonconfig, CloudStorageConfig.class);
        model.addAttribute("config", config);
        return prefix + "/config";
    }

    /**
     * 保存云存储配置信息
     */
    @RequestMapping("saveConfig")
    @RequiresPermissions("sys:oss:config")
    @ResponseBody
    public AjaxResult saveConfig(CloudStorageConfig config) {
        // 校验类型
        ValidatorUtils.validateEntity(config);
        if (config.getType() == CloudService.QINIU.getValue()) {
            // 校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        } else if (config.getType() == CloudService.ALIYUN.getValue()) {
            // 校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        } else if (config.getType() == CloudService.QCLOUD.getValue()) {
            // 校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        } else if (config.getType() == CloudService.FASTDFS.getValue()) {
            // TODO 校验 FASTDFS
        }
        return toAjax(sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config)));
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    //@RequiresPermissions("sys:oss:add")
    @ResponseBody
    public AjaxResult upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new OssException("上传文件不能为空");
        }
        // 上传文件
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        CloudStorageService storage = OSSFactory.build();
        String url = "";
        if (4 != CloudService.FASTDFS.getValue()) {
            url = storage.uploadSuffix(file.getBytes(), suffix);
        } else {
            url = storage.uploadSuffix(file, suffix);
        }
        // 保存文件信息
        SysOss ossEntity = new SysOss();
        ossEntity.setFileSuffix(suffix);
        try {
            ossEntity.setCreateBy(ShiroUtils.getLoginName());
        } catch (Exception e) {
            ossEntity.setCreateBy("");
        }
        ossEntity.setFileName(fileName);
        ossEntity.setCreateTime(new Date());
        ossEntity.setService(storage.getService());
        if (4 != CloudService.FASTDFS.getValue()) {
            ossEntity.setUrl(url);
        } else {
            ossEntity.setPath(url);
            ossEntity.setUrl(url.replace(";", ""));
        }
        return toAjax(sysOssService.save(ossEntity)).put("url", ossEntity.getUrl()).put("fileName", ossEntity.getFileName()).put("path", ossEntity.getPath());
    }

    /**
     * 修改
     */
    @GetMapping("edit/{ossId}")
    @RequiresPermissions("sys:oss:edit")
    public String edit(@PathVariable("ossId") Long ossId, Model model) {
        SysOss sysOss = sysOssService.findById(ossId);
        model.addAttribute("sysOss", sysOss);
        return prefix + "/edit";
    }

    @GetMapping("editor")
    @RequiresPermissions("sys:oss:add")
    public String editor() {
        return prefix + "/editor";
    }

    @GetMapping("uploadTest")
    @RequiresPermissions("sys:oss:add")
    public String uploadTest() {
        return prefix + "/upload";
    }

    /**
     * 修改
     */
    @PostMapping("edit")
    @RequiresPermissions("sys:oss:edit")
    @ResponseBody
    public AjaxResult editSave(SysOss sysOss) {
        return toAjax(sysOssService.update(sysOss));
    }

    /**
     * 删除
     */
    @RequestMapping("remove")
    @RequiresPermissions("sys:oss:remove")
    @ResponseBody
    public AjaxResult delete(String ids) {
        return toAjax(sysOssService.deleteByIds(ids));
    }

    /**
     * 下载文件
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */

    @RequestMapping("/download")
    @RequiresPermissions("sys:oss:download")
    @ResponseBody
    public String download(HttpServletRequest request,
                           HttpServletResponse response) throws UnsupportedEncodingException {
        CloudStorageService storage = OSSFactory.build();
        String fileUrl = request.getParameter("fileUrl");
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1); //下载的文件名

        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            //调用service层封装的下载方法
            Map<String, Object> result = storage.downloadFile(fileUrl);
            InputStream in = (InputStream) result.get("ins");
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader("Content-Length", String.valueOf(result.get("size")));
            // 实现文件下载
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = null;
            try {
                bis = new BufferedInputStream(in);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                //log.info("下载文件成功!");
            } catch (Exception e) {
                //log.info("下载文件失败!");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return fileName;
    }

}
