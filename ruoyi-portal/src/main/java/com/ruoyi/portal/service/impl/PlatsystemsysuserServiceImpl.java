package com.ruoyi.portal.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.portal.domain.Platsystemsysuser;
import com.ruoyi.portal.mapper.PlatsystemsysuserMapper;
import com.ruoyi.portal.service.PlatsystemsysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatsystemsysuserServiceImpl implements PlatsystemsysuserService {
    @Autowired
    PlatsystemsysuserMapper platsystemsysuserMapper;

    @Override
    public String checkPlatSysNameUnique(String loginName) {
        int count = platsystemsysuserMapper.checkPlatSysNameUnique(loginName);
        if (count > 0)
        {
            return "1";
        }
        return "0";
    }
    /**
     * 校验用户名称是否唯一
     *
     * @param
     * @return
     */
    @Override
    public String checkPhoneUnique(Platsystemsysuser platsystemsysuser)
    {
        String userId = StringUtils.isNull(platsystemsysuser.getSysuserId()) ? "" : platsystemsysuser.getSysuserId();
        Platsystemsysuser info = platsystemsysuserMapper.checkPPhoneUnique(platsystemsysuser.getSysuserMobile());
        if (StringUtils.isNotNull(info) && !info.getSysuserId().equals(userId))
        {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    @Override
    public int checkCardUnique(String sysuserIdCardNo) {
        return platsystemsysuserMapper.checkCardUnique(sysuserIdCardNo);
    }

    @Override
    public int savePSS(Platsystemsysuser platsystemsysuser) {
        return platsystemsysuserMapper.insert(platsystemsysuser);
    }

}
