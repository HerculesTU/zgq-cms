package com.ruoyi.portal.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.portal.domain.Tbenterpriseuser;
import com.ruoyi.portal.service.TbenterpriseuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/portal/login")
public class LoginController extends BaseController {
    @Autowired
    TbenterpriseuserService tbenterpriseuserService;
    /**
     * 登录
     * @param mode
     * @param username
     * @param password
     * @param
     * @return
     */
    @PostMapping("/qtAjaxLogin") //@Validated
    @ResponseBody
    public AjaxResult qtAjaxLogin(Model mode, @RequestParam String username, @RequestParam String password, HttpServletRequest request,HttpServletResponse response){
        try {
            Tbenterpriseuser tbenterpriseuser= tbenterpriseuserService.searchTb(username,password);
            if(tbenterpriseuser==null){
                return AjaxResult.error("登录失败，账户或密码错误");
            }
            mode.addAttribute("tbenterpriseuser",tbenterpriseuser);
            //String str= JSON.toJSONString(tbenterpriseuser);
            //return redirect("/portal/corporate");
            //response.getWriter().print(str);
            request.getSession().setAttribute("tbenterpriseuser",tbenterpriseuser);
            return AjaxResult.success("登录成功",tbenterpriseuser);
        } catch (Exception e) {
           /// String msg = "用户或密码错误";
            //return error(msg);
            //return "redirect:/";
            return AjaxResult.error("登录失败");
        }
    }
}
