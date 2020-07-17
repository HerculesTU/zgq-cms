package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TbenterpriseuserMapper {
    int deleteByPrimaryKey(String enterpriseId);

    int insert(Tbenterpriseuser record);

    int insertSelective(Tbenterpriseuser record);

    Tbenterpriseuser selectByPrimaryKey(String enterpriseId);

    int updateByPrimaryKeySelective(Tbenterpriseuser record);

    int updateByPrimaryKey(Tbenterpriseuser record);


    /**
     * 保存基本信息
     * @param basicInformation
     * @return
     */
    int insert_basicInformation(BasicInformation basicInformation);

    Tbenterpriseuser searchTbuser(@Param("username")String username, @Param("password")String password);

    /**
     * 企业注册第二步更新候选工会主席
     * @return
     */
    int updateByPrimaryKeyOwer(FirstGeneralMeeting firstGeneralMeeting);

    /**
     * 更新选举结果报告
     * @param electionResults
     * @return
     */
    int updateXJDHBG(ElectionResults electionResults);

    /**
     * 刻制工会公章（第四步）
     * @param engraveUnionSeal
     * @return
     */
    int updateKZGHGZ(EngraveUnionSeal engraveUnionSeal);
    /**
     * 第5步法人资格证
     * @return
     */
    int updateFRZGZ(@Param("enterpriseId")String id,
                    @Param("registrationLegalPerson")String registrationlegal,
                    @Param("pointsNote")String pointnote);

    /**
     * 注册用户唯一性校验
     * @param loginName
     * @return
     */
    int checkenterpriseAccountUnique(String loginName);

}