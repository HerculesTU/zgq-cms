package com.ruoyi.portal.service;

import com.ruoyi.portal.domain.*;
import com.ruoyi.system.domain.SysDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbenterpriseuserService {

    /**
     * 保存
     * @param tbenterpriseuser
     * @return
     */
    public int savePSS(Tbenterpriseuser tbenterpriseuser);

    /**
     * 保存基础数据
     * @param basicInformation
     * @return
     */
    public int saveBasicInfomation(BasicInformation basicInformation);

    /**
     * 查询登录名是否唯一
     * @param ACCOUNTName
     * @return
     */
    public boolean checkACCOUNTNameUnique(String ACCOUNTName);

    /**
     * 查询工会个人信息是否唯一
     * @param realname
     * @return
     */
    public boolean checkRealNAMEUnique(String realname,String CARDNO);

    /**
     * 查询企业名称是否唯一
     * @param ENTERPRISENAME
     * @return
     */
    public boolean checkENTERPRISENAMEUnique(String ENTERPRISENAME);

    /**
     * 查询个人姓名与筹备组组长
     * @param CARDNO
     * @param LEGAL_PERSON_ID_CARD
     * @param PREPARATORY_GROUP_USER_ID_CARD
     * 企业行政负责人、法人、合伙人及其近亲属、人力资源部门负责人、外籍员工不得作为本企业筹备组组长候选人
     * PREPARATORY_GROUP_USER_ID_CARD！=CARDNO && PREPARATORY_GROUP_USER_ID_CARD！=LEGAL_PERSON_ID_CARD
     * @return
     */
    public boolean checkRealNAMEIsOrNotENTERPRISENAME(String CARDNO,String LEGAL_PERSON_ID_CARD,String PREPARATORY_GROUP_USER_ID_CARD);

    /**
     * 登录查询
     * @param username
     * @param password
     * @return
     */
    public Tbenterpriseuser searchTb(String username, String password);

    /**
     * 更新开会候选主席以及开会视频信息
     * @return
     */
    int updateGHZX(FirstGeneralMeeting firstGeneralMeeting);

    /**
     * 第一次选举大会结果报告更新
     * @return
     */
    int updateXJDHBG(ElectionResults electionResults);

    /**
     * \第4步
     * @param engraveUnionSeal
     * @return
     */
    int updateKZGHGZ(EngraveUnionSeal engraveUnionSeal);
    /**
     * 第5步法人资格证
     * @param id
     * @param registrationlegal
     * @param pointnote
     * @return
     */
    int updateFRZGZ(String id,String registrationlegal,String pointnote);

    /**
     * 保存企业注册接口返回值
     * @param interResponseBody
     * @return
     */
    int saveInterResponseBody(InterResponseBody interResponseBody);

    /**
     * 根据fid查询反馈接口反馈信息
     * @param fid
     * @return
     */
    List<InterResponseBody> searchInfoByFid(String fid);

    /**
     * 测试
     * @param enterpriseId
     * @return
     */
    Tbenterpriseuser selectByPrimaryKey(String enterpriseId);

    /**
     * 登录用户名校验
     * @param loginName
     * @return
     */
    int checkenterpriseAccountUnique(String loginName);

    /**
     * 查询下拉按钮
     * @param dicttype
     * @return
     */
    List<SysDictData> searchLabelByType(String dicttype);
}
