package com.ruoyi.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.TypeUtils;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.portal.config.JiekouUrlConfig;
import com.ruoyi.portal.domain.*;
import com.ruoyi.portal.service.TbenterpriseuserService;
import com.ruoyi.portal.service.impl.RestTemplateToInterface;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.third.domain.SmsHis;
import com.ruoyi.third.service.ISmsHisService;
import org.apache.commons.collections.MultiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping("/portal/tbenterpriseuser")
public class TbenterpriseuserController extends BaseController {
    @Autowired
    JiekouUrlConfig jiekouUrlConfig;

    @Autowired
    TbenterpriseuserService tbenterpriseuserService;
    @Autowired
    private RestTemplateToInterface restTemplateToInterface;
    @Autowired
    private ISmsHisService iSmsHisService;

    @PostMapping("/add") //@Validated
    @ResponseBody
    public AjaxResult addPlatSysSave(Tbenterpriseuser user, HttpServletRequest request, HttpServletRequest response)
    {
        tbenterpriseuserService.savePSS(user);
        return toAjax(0);
    }

    @PostMapping("/searchReturnInfo") //@Validated
    @ResponseBody
    public AjaxResult searchReturnInfo(@RequestParam String id)
    {
        //id为null则正在注册基本信息，肯定的保存信息并条用下一步接口
        Map<String,String> mp=new HashMap<String,String>();
        String nodeKeys="";
        String jbpmExeId="";
        if(id==null || "".equals(id)){
            mp.put("nodeKeys","");
            mp.put("jbpmExeId","");
            mp.put("bs","0");
        }else {
            List<InterResponseBody> ls = tbenterpriseuserService.searchInfoByFid(id);
            if(ls.size()>0){
                InterResponseBody interResponseBody=ls.get(0);
                String nextStepList_str=interResponseBody.getNextsteplist();
                JSONArray array=JSONArray.parseArray(nextStepList_str);
                JSONObject obj=array.getJSONObject(0);
                ResponseNextStep responseNextStep=JSON.toJavaObject(obj,ResponseNextStep.class);
                //ResponseNextStep responseNextStep=JSONObject.parseObject(nextStepList_str,new TypeReference<ResponseNextStep>(){});
                //下一步的参数
                nodeKeys=responseNextStep==null?"":responseNextStep.getNodeKeys();
                jbpmExeId=interResponseBody.getJbpmexeid();
                int bs=interResponseBody.getBs();
                mp.put("nodeKeys",nodeKeys);
                mp.put("jbpmExeId",jbpmExeId);
                mp.put("bs",String.valueOf(bs));
            }
        }
        return  AjaxResult.success("正在提交",mp);
    }

    /**
     * 第一步保存基本信息
     * @param user
     * @return
     */
    //@RequiresPermissions("tbenterpriseuser:addBaseInfor")
    @PostMapping("/addBaseInfor") //@Validated
    @ResponseBody
    public AjaxResult addTbenterpriseUserSave(BasicInformation user)
    {
        Basic_information basic_information=new Basic_information();
        basic_information.setENTERPRISE_ID(user.getEnterpriseId());
        basic_information.setENTERPRISE_ACCOUNT(user.getEnterpriseAccount());
        basic_information.setENTERPRISE_PASSWORD(user.getEnterprisePassword());
        basic_information.setADMIN_NAME(user.getAdminName());
        basic_information.setADMIN_SEX(user.getAdminSex());
        basic_information.setADMIN_MOBILE(user.getAdminMobile());
        basic_information.setADMIN_ID_CARD_NO(user.getAdminIdCardNo());
        basic_information.setENTERPRISE_NAME(user.getEnterpriseName());
        basic_information.setENTERPRISE_CREDIT_CODE(user.getEnterpriseCreditCode());
        basic_information.setENTERPRISE_EMPLOYEE_NUM(user.getEnterpriseEmployeeNum());
        basic_information.setENTERPRISE_WOMEN_EMPLOYEE(user.getEnterpriseWomenEmployee());
        basic_information.setENTERPRISE_LEGAL_PERSON(user.getEnterpriseLegalPerson());
        basic_information.setLEGAL_PERSON_ID_CARD(user.getLegalPersonIdCard());
        basic_information.setENTERPRISE_NATURE(user.getEnterpriseNature());
        basic_information.setENTERPRISE_MANAGE_ITEM(user.getEnterpriseManageItem());
        basic_information.setENTERPRISE_PAY_TAXES_DISTRICT(user.getEnterprisePayTaxesDistrict());
        basic_information.setENTERPRISE_PARK(user.getEnterprisePark());
        basic_information.setENTERPRISE_REGISTER_PLACE(user.getEnterpriseRegisterPlace());
        basic_information.setENTERPRISE_OF_INDUSTRY(user.getEnterpriseOfIndustry());
        basic_information.setPREPARATORY_GROUP_USER(user.getPreparatoryGroupUser());
        basic_information.setPREPARATORY_GROUP_USER_ID_CARD(user.getPreparatoryGroupUserIdCard());
        basic_information.setUNION_CONTACTS(user.getUnionContacts());
        basic_information.setCONTACT_WAY(user.getContactWay());
        basic_information.setWORK_ADDRESS(user.getWorkAddress());
        basic_information.setMEMBER_NUM(user.getMemberNum());
/*        basic_information.setMEMBER_REGISTER(user.getMemberRegister());
        basic_information.setPREPARATORY_GROUP_LIST(user.getPreparatoryGroupList());
        basic_information.setTRADING_CERTIFICATE(user.getTradingCertificate());
        basic_information.setLEGAL_PERSON_ID_CARD_FILE(user.getLegalPersonIdCardFile());*/
        basic_information.setCREATE_TIME(user.getCreateTime());
        basic_information.setFile(user.getFile());

        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        TypeUtils.compatibleWithJavaBean=true;
        String str= com.alibaba.fastjson.JSONObject.toJSONString(basic_information);
        map.add("data", str);
        try {
            //"http://192.168.1.30:8091/business/enterprise/EnterpriseUserController/initiateEnterpriseFlow.do";
            String url = jiekouUrlConfig.getBasicjiekouurl();
            String responseBody = restTemplateToInterface.doPostWith1(url, map);
            /*String responseBody="{\n" +
                    "\t\"flowToken\": \"4028819e732820ea017328329a36000a\",\n" +
                    "\t\"code\": \"enterprise_initiation\",\n" +
                    "\t\"success\": true,\n" +
                    "\t\"nextStepList\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"branchSelectKey\": \"\",\n" +
                    "\t\t\t\"isBranch\": \"-1\",\n" +
                    "\t\t\t\"nodeAssignerList\": [\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"assignerIds\": \"4028819e73138873017313eade940036\",\n" +
                    "\t\t\t\t\t\"assignerNames\": \"郭雅欣\",\n" +
                    "\t\t\t\t\t\"defaultInter\": \"handlerConfigService.getAssignHandlers\",\n" +
                    "\t\t\t\t\t\"filterRule\": \"\",\n" +
                    "\t\t\t\t\t\"handlerHeight\": \"\",\n" +
                    "\t\t\t\t\t\"handlerNature\": \"1\",\n" +
                    "\t\t\t\t\t\"handlerType\": \"1\",\n" +
                    "\t\t\t\t\t\"handlerUrl\": \"\",\n" +
                    "\t\t\t\t\t\"handlerWidth\": \"\",\n" +
                    "\t\t\t\t\t\"isOrder\": \"-1\",\n" +
                    "\t\t\t\t\t\"nextAuditType\": \"1\",\n" +
                    "\t\t\t\t\t\"nextHandlers\": [\n" +
                    "\t\t\t\t\t\t{\n" +
                    "\t\t\t\t\t\t\t\"assignerCode\": \"\",\n" +
                    "\t\t\t\t\t\t\t\"assignerEmail\": \"\",\n" +
                    "\t\t\t\t\t\t\t\"assignerId\": \"4028819e73138873017313eade940036\",\n" +
                    "\t\t\t\t\t\t\t\"assignerMobile\": \"\",\n" +
                    "\t\t\t\t\t\t\t\"assignerName\": \"郭雅欣\",\n" +
                    "\t\t\t\t\t\t\t\"taskOrder\": -1\n" +
                    "\t\t\t\t\t\t}\n" +
                    "\t\t\t\t\t],\n" +
                    "\t\t\t\t\t\"nodeKey\": \"-6\",\n" +
                    "\t\t\t\t\t\"nodeName\": \"总工会管\\n理员审核\",\n" +
                    "\t\t\t\t\t\"varValues\": \"4028819e73138873017313eade940036\"\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t],\n" +
                    "\t\t\t\"nodeKeys\": \"-6\",       //作为下个节点提交时的参数\n" +
                    "\t\t\t\"nodeNames\": \"总工会管\\n理员审核\"\n" +
                    "\t\t}\n" +
                    "\t],\n" +
                    "\t\"nextAuditType\": \"1\",\n" +
                    "\t\"jbpmExeId\": \"SQBH202007071457090650\"\n" +
                    "}";*/
            JSONObject obj=JSONObject.parseObject(responseBody);

            //InterResponseBody rb=(InterResponseBody)JSONObject.parse(responseBody);
            InterResponseBody rb = JSON.parseObject(responseBody, new TypeReference<InterResponseBody>() {});

            String id=obj.get("ENTERPRISE_ID").toString();
            String rb_id=UUID.randomUUID().toString();
            rb.setId(rb_id);
            rb.setBs(1);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sf.format(new Date());
            rb.setCreattime(sf.format(new Date()));

            if(user.getEnterpriseId()==null || "".equals(user.getEnterpriseId())){
                user.setEnterpriseId(id);
                rb.setFid(id);
            }else{
                rb.setFid(user.getEnterpriseId());
            }
            //保存反馈信息
            tbenterpriseuserService.saveInterResponseBody(rb);
            //保存基本信息
            return toAjax(tbenterpriseuserService.saveBasicInfomation(user));
        } catch (Exception e) {
            e.printStackTrace();
            return toAjax(0);
        }
    }

    /**
     * 更新保存第一次会员代表大会（第二步）
     * @return
     */
    @PostMapping("/updateGHZX") //@Validated
    @ResponseBody
    public AjaxResult updateGHZX(FirstGeneralMeeting firstGeneralMeeting)
    {
        First_General_Meeting first_General_Meeting=new First_General_Meeting();
        first_General_Meeting.setCANDIDATE_UNION_CHAIRMAN(firstGeneralMeeting.getCandidateUnionChairman());
        first_General_Meeting.setMEETING_TIME1(firstGeneralMeeting.getMeetingTime1());
        first_General_Meeting.setMEETING_NUM(
                firstGeneralMeeting.getMeetingNum()==null?"":String.valueOf(firstGeneralMeeting.getMeetingNum()));
        first_General_Meeting.setCOMMITTEE_MEMBER1(
                firstGeneralMeeting.getCommitteeMember1()==null?"":String.valueOf(firstGeneralMeeting.getCommitteeMember1()));
        first_General_Meeting.setCANDIDATE1(
                firstGeneralMeeting.getCandidate1()==null?"":String.valueOf(firstGeneralMeeting.getCandidate1()));
        first_General_Meeting.setDIFFERENCE(
                firstGeneralMeeting.getDifference()==null?"":String.valueOf(firstGeneralMeeting.getDifference()));
        first_General_Meeting.setUNION_DEPUTY_CHAIRMAN(
                firstGeneralMeeting.getUnionDeputyChairman()==null?"":String.valueOf(firstGeneralMeeting.getUnionDeputyChairman()));
        first_General_Meeting.setEXPENDITURE_NUM(
                firstGeneralMeeting.getExpenditureNum()==null?"":String.valueOf(firstGeneralMeeting.getExpenditureNum()));
        first_General_Meeting.setWOMEN_COMMITTEE(
                firstGeneralMeeting.getWomenCommittee()==null?"":String.valueOf(firstGeneralMeeting.getWomenCommittee()));
        first_General_Meeting.setWOMEN_COMMITTEE_NUM(
                firstGeneralMeeting.getWomenCommitteeNum()==null?"":String.valueOf(firstGeneralMeeting.getWomenCommitteeNum()));
        first_General_Meeting.setUNION_COMMITTEES(firstGeneralMeeting.getUnionCommittees());
        first_General_Meeting.setEXPENDITURE_COMMITTEES(firstGeneralMeeting.getExpenditureCommittees());
        first_General_Meeting.setWOMEN_COMMITTEES(firstGeneralMeeting.getWomenCommittees());
//        first_General_Meeting.setVIDEO_MEETING(firstGeneralMeeting.getVideoMeeting());
//        first_General_Meeting.setPHOTO_MEETING(firstGeneralMeeting.getPhotoMeeting());
        first_General_Meeting.setFile(firstGeneralMeeting.getFile());
        //查询第一步返回数据，获取下面的2个值，并赋值
        first_General_Meeting.setExeId(firstGeneralMeeting.getJbpmExeId());
        first_General_Meeting.setFormNodeKey(firstGeneralMeeting.getNodeKeys());
        first_General_Meeting.setENTERPRISE_ID(firstGeneralMeeting.getEnterpriseId());
        first_General_Meeting.setEXPENDITURE(firstGeneralMeeting.getExpenditure());

        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        TypeUtils.compatibleWithJavaBean=true;
        String str= com.alibaba.fastjson.JSONObject.toJSONString(first_General_Meeting);
        map.add("data", str);

        try {
            //"http://192.168.1.30:8091/business/enterprise/EnterpriseUserController/exeEnterpriseFlow.do"
            String url=jiekouUrlConfig.getQtjiekouurl();
            String responseBody = restTemplateToInterface.doPostWith1(url,map);
            JSONObject obj=JSONObject.parseObject(responseBody);
            InterResponseBody rb = JSON.parseObject(responseBody, new TypeReference<InterResponseBody>() {});
            String rb_id=UUID.randomUUID().toString();
            rb.setId(rb_id);
            rb.setBs(2);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sf.format(new Date());
            rb.setCreattime(sf.format(new Date()));
            rb.setFid(firstGeneralMeeting.getEnterpriseId());
            //保存反馈信息
            tbenterpriseuserService.saveInterResponseBody(rb);

            return toAjax(tbenterpriseuserService.updateGHZX(firstGeneralMeeting));
        } catch (Exception e) {
            e.printStackTrace();
            return toAjax(0);
        }
    }

    /**
     * 第一次选举大会结果报告更新（第三步）
     * @return
     */
    @PostMapping("/updateXJDHBG") //@Validated
    @ResponseBody
    public AjaxResult updateXJDHBG(ElectionResults electionResults)
    {
        Election_Results election_Results=new Election_Results();
        election_Results.setUNION_CHAIRMAN(electionResults.getUnionChairman());
        election_Results.setEXPENDITURE_DIRECTOR(electionResults.getExpenditureDirector());
        election_Results.setWOMEN_DIRECTOR(electionResults.getWomenDirector());
        election_Results.setMEETING_TIME2(electionResults.getMeetingTime2());
        election_Results.setMEETING_SITE(electionResults.getMeetingSite());
        election_Results.setYD_NUM(electionResults.getYdNum());
        election_Results.setSD_NUM(electionResults.getSdNum());
        election_Results.setCOMMITTEES(electionResults.getCommittees());
        election_Results.setREAL_EXPENDITURE_COMMITTEES(electionResults.getRealExpenditureCommittees());
//        election_Results.setELECTION_RESULTS_REPORT(electionResults.getElectionResultsReport());
//        election_Results.setMEMBERSHIP_TRADE_COMMITTEES(electionResults.getMembershipTradeCommittees());
//        election_Results.setLETTER_COMMITMENT_PRESIDENT(electionResults.getLetterCommitmentPresident());
//        election_Results.setVOTES_OBTAINED(electionResults.getVotesObtained());
//        election_Results.setREGISTRATION_GRASS_TRADE(electionResults.getRegistrationGrassTrade());
//        election_Results.setSUMMARY_TABLE_WOMEN_COMMITTEES(electionResults.getSummaryTableWomenCommittees());
        //查询第一步返回数据，获取下面的2个值，并赋值
        election_Results.setExeId(electionResults.getJbpmExeId());
        election_Results.setFormNodeKey(electionResults.getNodeKeys());
        election_Results.setFile(electionResults.getFile());
        election_Results.setENTERPRISE_ID(electionResults.getEnterpriseId());


        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        TypeUtils.compatibleWithJavaBean=true;
        String str= com.alibaba.fastjson.JSONObject.toJSONString(election_Results);
        map.add("data", str);

        try {
            //String url="http://192.168.1.30:8091/business/enterprise/EnterpriseUserController/exeEnterpriseFlow.do";
            String url=jiekouUrlConfig.getQtjiekouurl();
            String responseBody = restTemplateToInterface.doPostWith1(url,map);
            InterResponseBody rb = JSON.parseObject(responseBody, new TypeReference<InterResponseBody>() {});
            String rb_id=UUID.randomUUID().toString();
            rb.setId(rb_id);
            rb.setBs(3);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sf.format(new Date());
            rb.setCreattime(sf.format(new Date()));
            rb.setFid(electionResults.getEnterpriseId());
            //保存反馈信息
            tbenterpriseuserService.saveInterResponseBody(rb);
            return toAjax(tbenterpriseuserService.updateXJDHBG(electionResults));
        } catch (Exception e) {
            e.printStackTrace();
            return toAjax(0);
        }

    }

    /**
     * 刻制工会公章（第四步）
     * @return
     */
    @PostMapping("/updateKZGHGZ") //@Validated
    @ResponseBody
    public AjaxResult updateKZGHGZ(EngraveUnionSeal engraveUnionSeal)
    {
        Engrave_UnionSeal engrave_UnionSeal=new Engrave_UnionSeal();
        engrave_UnionSeal.setOPERATOR_CONTACTS(engraveUnionSeal.getOperatorContacts());
//        engrave_UnionSeal.setUNION_PRESIDENT_IDCARD(engraveUnionSeal.getUnionPresidentIdcard());
//        engrave_UnionSeal.setCORPORATE_IDENTITY_CARD(engraveUnionSeal.getCorporateIdentityCard());
//        engrave_UnionSeal.setELECTRONIC_VERSION(engraveUnionSeal.getElectronicVersion());
//        engrave_UnionSeal.setIDENTITY_CARD_AGENT(engraveUnionSeal.getIdentityCardAgent());
          engrave_UnionSeal.setFile(engraveUnionSeal.getFile());

        //查询第一步返回数据，获取下面的2个值，并赋值
        engrave_UnionSeal.setExeId(engraveUnionSeal.getJbpmExeId());
        engrave_UnionSeal.setFormNodeKey(engraveUnionSeal.getNodeKeys());
        engrave_UnionSeal.setENTERPRISE_ID(engraveUnionSeal.getEnterpriseId());

        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        TypeUtils.compatibleWithJavaBean=true;
        String str= com.alibaba.fastjson.JSONObject.toJSONString(engrave_UnionSeal);
        map.add("data", str);

        try {
            //String url="http://192.168.1.30:8091/business/enterprise/EnterpriseUserController/exeEnterpriseFlow.do";
            String url=jiekouUrlConfig.getQtjiekouurl();
            String responseBody = restTemplateToInterface.doPostWith1(url,map);

            InterResponseBody rb = JSON.parseObject(responseBody, new TypeReference<InterResponseBody>() {});
            String rb_id=UUID.randomUUID().toString();
            rb.setId(rb_id);
            rb.setBs(4);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sf.format(new Date());
            rb.setCreattime(sf.format(new Date()));
            rb.setFid(engraveUnionSeal.getEnterpriseId());
            //保存反馈信息
            tbenterpriseuserService.saveInterResponseBody(rb);
            return toAjax(tbenterpriseuserService.updateKZGHGZ(engraveUnionSeal));
        } catch (Exception e) {
            e.printStackTrace();
            return toAjax(0);
        }

    }

    /**
     * 第5步法人资格证
     * @param id
     * @param
     * @param
     * @return
     */
    @PostMapping("/updateFRZGZ") //@Validated
    @ResponseBody
    public AjaxResult updateFRZGZ(@RequestParam String id,@RequestParam String registrationlegal1,@RequestParam String pointnote1,
                                  @RequestParam String jbpmExeId,@RequestParam String nodeKeys)
    {
        //查询上一步返回数据，获取下面的2个值，并赋值
//        String exeId="";
//        String formNodeKey="";
        JSONObject obj=new JSONObject();
        obj.put("exeId",jbpmExeId);
        obj.put("formNodeKey",nodeKeys);
        obj.put("ENTERPRISE_ID",id);

        JSONObject obj1=JSON.parseObject(registrationlegal1);
        JSONObject obj2=JSON.parseObject(pointnote1);
        String registrationlegal=obj1.get("url").toString();
        String pointnote=obj2.get("url").toString();

        JSONObject obj3=new JSONObject();
        obj3.put("dbfilepath",obj1.get("path"));
        obj3.put("originalfilename",obj1.get("fileName"));
        obj3.put("ATTACHMENT_ID","17");

        JSONObject obj4=new JSONObject();
        obj4.put("dbfilepath",obj2.get("path"));
        obj4.put("originalfilename",obj2.get("fileName"));
        obj4.put("ATTACHMENT_ID","18");

        JSONArray arry=new JSONArray();
        arry.add(obj3);
        arry.add(obj4);
        obj.put("file",arry.toJSONString());
//        obj.put("REGISTRATION_LEGAL_PERSON",registrationlegal);
//        obj.put("POINTS_NOTE",pointnote);


        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        TypeUtils.compatibleWithJavaBean=true;
        String str= obj.toJSONString();
        map.add("data", str);

        try {
            //String url="http://192.168.1.30:8091/business/enterprise/EnterpriseUserController/exeEnterpriseFlow.do";
            String url=jiekouUrlConfig.getQtjiekouurl();
            String responseBody = restTemplateToInterface.doPostWith1(url,map);

            InterResponseBody rb = JSON.parseObject(responseBody, new TypeReference<InterResponseBody>() {});
            String rb_id=UUID.randomUUID().toString();
            rb.setId(rb_id);
            rb.setBs(5);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sf.format(new Date());
            rb.setCreattime(sf.format(new Date()));
            rb.setFid(id);
            //保存反馈信息
            tbenterpriseuserService.saveInterResponseBody(rb);
            return toAjax(tbenterpriseuserService.updateFRZGZ(id,registrationlegal,pointnote));
        } catch (Exception e) {
            e.printStackTrace();
            return toAjax(0);
        }


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
        int i=tbenterpriseuserService.checkenterpriseAccountUnique(loginName);
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

    /**
     * 校验用户名
     */
    //@PostMapping("/searchLabelByType")
    @RequestMapping(value = "/searchLabelByType",method = RequestMethod.POST,produces="application/json;charset=UTF-8;")
    @ResponseBody
    public void searchLabelByType(@RequestParam String dicttype, HttpServletResponse response)
    {
        List<SysDictData> ls= null;
//        try {
//            ls = tbenterpriseuserService.searchLabelByType(dicttype);
//            AjaxResult.success("查询成功",ls);
//        } catch (Exception e) {
//            e.printStackTrace();
//            AjaxResult.error("下拉按钮查询失败");
//        }

        ls = tbenterpriseuserService.searchLabelByType(dicttype);
        String str=JSON.toJSONString(ls);
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
