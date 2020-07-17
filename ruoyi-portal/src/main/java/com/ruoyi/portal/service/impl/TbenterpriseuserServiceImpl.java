package com.ruoyi.portal.service.impl;

import com.ruoyi.portal.domain.*;
import com.ruoyi.portal.mapper.InterResponseBodyMapper;
import com.ruoyi.portal.mapper.TbenterpriseuserMapper;
import com.ruoyi.portal.service.TbenterpriseuserService;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TbenterpriseuserServiceImpl implements TbenterpriseuserService {

    @Autowired
    TbenterpriseuserMapper tbenterpriseuserMapper;
    @Autowired
    InterResponseBodyMapper interResponseBodyMapper;
    @Autowired
    SysDictDataMapper sysDictDataMapper;
    @Override
    public int savePSS(Tbenterpriseuser tbenterpriseuser) {
        return tbenterpriseuserMapper.insert(tbenterpriseuser);
    }

    @Override
    public boolean checkACCOUNTNameUnique(String ACCOUNTName) {
        return false;
    }

    @Override
    public boolean checkRealNAMEUnique(String realname, String CARDNO) {
        return false;
    }

    @Override
    public boolean checkENTERPRISENAMEUnique(String ENTERPRISENAME) {
        return false;
    }

    @Override
    public boolean checkRealNAMEIsOrNotENTERPRISENAME(String CARDNO, String LEGAL_PERSON_ID_CARD,
                                                      String PREPARATORY_GROUP_USER_ID_CARD) {
        return false;
    }

    @Override
    public int saveBasicInfomation(BasicInformation basicInformation) {
        return tbenterpriseuserMapper.insert_basicInformation(basicInformation);
    }

    @Override
    public Tbenterpriseuser searchTb(String username, String password) {//
        return tbenterpriseuserMapper.searchTbuser(username,password);//
    }

    @Override
    public int updateGHZX(FirstGeneralMeeting firstGeneralMeeting) {
        return tbenterpriseuserMapper.updateByPrimaryKeyOwer(firstGeneralMeeting);
    }

    @Override
    public int updateXJDHBG(ElectionResults electionResults) {
        return tbenterpriseuserMapper.updateXJDHBG(electionResults);
    }

    @Override
    public int updateKZGHGZ(EngraveUnionSeal engraveUnionSeal) {
        return tbenterpriseuserMapper.updateKZGHGZ(engraveUnionSeal);
    }

    @Override
    public int updateFRZGZ(String id, String registrationlegal, String pointnote) {
        return tbenterpriseuserMapper.updateFRZGZ(id,registrationlegal,pointnote);
    }

    @Override
    public int saveInterResponseBody(InterResponseBody interResponseBody) {
        return interResponseBodyMapper.insert(interResponseBody);
    }

    @Override
    public List<InterResponseBody> searchInfoByFid(String fid) {
        return interResponseBodyMapper.selectByfid(fid);
    }

    @Override
    public Tbenterpriseuser selectByPrimaryKey(String enterpriseId) {
        return tbenterpriseuserMapper.selectByPrimaryKey(enterpriseId);
    }

    @Override
    public int checkenterpriseAccountUnique(String loginName) {
        return tbenterpriseuserMapper.checkenterpriseAccountUnique(loginName);
    }

    @Override
    public List<SysDictData> searchLabelByType(String dicttype) {
        return sysDictDataMapper.searchLabelByType(dicttype);
    }

}
