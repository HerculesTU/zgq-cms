package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.Platsystemsysuser;
import com.ruoyi.system.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
public interface PlatsystemsysuserMapper {
    int deleteByPrimaryKey(String sysuserId);

    int insert(Platsystemsysuser record);

    int insertSelective(Platsystemsysuser record);

    Platsystemsysuser selectByPrimaryKey(String sysuserId);

    int updateByPrimaryKeySelective(Platsystemsysuser record);

    int updateByPrimaryKeyWithBLOBs(Platsystemsysuser record);

    int updateByPrimaryKey(Platsystemsysuser record);
    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    public int checkPlatSysNameUnique(String loginName);
    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public Platsystemsysuser checkPPhoneUnique(String phonenumber);

    /**
     * 身份证查询
     * @param sysuserIdCardNo
     * @return
     */
    public int checkCardUnique(String sysuserIdCardNo);
}