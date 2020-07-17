package com.ruoyi.portal.controller;


import com.alibaba.fastjson.util.TypeUtils;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.portal.domain.Platsystemsysuser;
import com.ruoyi.portal.domain.plat_system_sysuser;
import com.ruoyi.portal.service.PlatsystemsysuserService;
import com.ruoyi.portal.service.impl.RestTemplateToInterface;
import com.ruoyi.third.domain.SmsHis;
import com.ruoyi.third.service.ISmsHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/portal/platsystem")
public class PlatsystemuserController extends BaseController {

    @Autowired
    private PlatsystemsysuserService platsystemsysuserService;
    @Autowired
    private ISmsHisService iSmsHisService;

    @Autowired
    private RestTemplateToInterface restTemplateToInterface;

    @Log(title = "前端用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add") //@Validated
    @ResponseBody
    public AjaxResult addPlatSysSave(Platsystemsysuser user)
    {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(platsystemsysuserService.checkPlatSysNameUnique(user.getSysuserAccount())))
        {
            return error("新增用户'" + user.getSysuserAccount() + "'失败，登录账号已存在");
        }
        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(platsystemsysuserService.checkPhoneUnique(user)))
        {
            return error("新增用户'" + user.getSysuserAccount() + "'失败，手机号码已存在");
        }
//        String id=user.getSysuserId();
//        if(id==null || "".equals(id)){
//            user.setSysuserId(UUID.randomUUID().toString());
//        }
        plat_system_sysuser pluser=new plat_system_sysuser();
        pluser.setSYSUSER_ID(user.getSysuserId());
        pluser.setSYSUSER_ACCOUNT(user.getSysuserAccount());
        pluser.setSYSUSER_NAME(user.getSysuserName());
        pluser.setSYSUSER_STATUS(user.getSysuserStatus()==null?"":user.getSysuserStatus().toString());
        pluser.setSYSUSER_CREATETIME(user.getSysuserCreatetime()==null?"":user.getSysuserCreatetime().toString());
        pluser.setSYSUSER_PASSWORD(user.getSysuserPassword());
        pluser.setSYSUSER_SEX(user.getSysuserSex());
        pluser.setSYSUSER_ACADEMIC_CAREER(user.getSysuserAcademicCareer());
        pluser.setSYSUSER_MOBILE(user.getSysuserMobile());
        pluser.setSYSUSER_COMPANYID(user.getSysuserCompanyid());
        pluser.setSYSUSER_COMPANY_NAME(user.getSysuserCompanyName());
        pluser.setSYSUSER_DEPARTID(user.getSysuserDepartid());
        pluser.setSYSUSER_SIGN(user.getSysuserSign());
        //pluser.setSYSUSER_SN(user.getSysuserSn()==null?"":user.getSysuserSn().toString());
        pluser.setSYSUSER_GROUPID(user.getSysuserGroupid());
        pluser.setSYSUSER_THEMECOLOR(user.getSysuserThemecolor());
        pluser.setSYSUSER_MENUTYPE(user.getSysuserMenutype());
        pluser.setSYSUSER_ENTERPRISE_NAME(user.getSysuserEnterpriseName());
        pluser.setCREDIT_CODE(user.getCreditCode());
        pluser.setSYSUSER_ID_CARD_NO(user.getSysuserIdCardNo());
        pluser.setIS_TEMP(user.getIsTemp());
        pluser.setSYSUSER_RIGHTJSON(user.getSysuserRightjson());
        //pluser.setUploadImg(user.getUploadImg());
        pluser.setFile(user.getUploadImg());

        //Map<String,Object> hm=new HashMap<String,Object>();

        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        TypeUtils.compatibleWithJavaBean=true;
        String str= com.alibaba.fastjson.JSONObject.toJSONString(pluser);
        paramMap.add("data", str);
        //使转换时首字母不小写


        System.out.printf("str");

        try {
            String url="http://192.168.1.30:8091/business/member/MemberController/initiatePersonalFlow.do";
            restTemplateToInterface.doPostWith1(url,paramMap);
            return toAjax(1);
        } catch (Exception e) {
            e.printStackTrace();
            return toAjax(0);
        }
/*        if(){
            //调用接口成功
            return toAjax(0);
        }else{
            //调用接口失败
            return toAjax(-1);
        }*/
        //return toAjax(platsystemsysuserService.savePSS(user));
    }

    @PostMapping("/checkPhoneNum")
    @ResponseBody
    public AjaxResult chechPhoneNum(@RequestParam String yzm,@RequestParam String phone){
        String sjc=String.valueOf(System.currentTimeMillis()/1000);
        List<SmsHis> list =iSmsHisService.selectSmsHisList2(phone,sjc);
        String con="";
        if(list.size()>0){
            SmsHis sm=list.get(0);
            con=sm.getContent();
            if(yzm.equals(con)){
              return  toAjax(0);
            }
        }
        return toAjax(-1);
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public void checkLoginNameUnique(@RequestParam String loginName, HttpServletResponse response)
    {
        String i=platsystemsysuserService.checkPlatSysNameUnique(loginName);
        int j= Integer.valueOf(i);
        boolean flg=false;
        if(j<=0){
            flg=true;
        }
        try {
            response.getWriter().print(flg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(Platsystemsysuser user)
    {
        return platsystemsysuserService.checkPhoneUnique(user);
    }

    @PostMapping("/checkCradUnique")
    @ResponseBody
    public void checkCradUnique(Platsystemsysuser user, HttpServletResponse response)
    {
        int i=platsystemsysuserService.checkCardUnique(user.getSysuserIdCardNo());
       boolean flg=false;
        if(i<=0){
            flg=true;
        }
        try {
            response.getWriter().print(flg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
