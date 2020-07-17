package com.ruoyi.portal.service;

import com.ruoyi.portal.domain.Platsystemsysuser;

public interface PlatsystemsysuserService {
    /**
     * 保存个人企业注册信息
     * @param
     * @return
     */
    public int savePSS(Platsystemsysuser platsystemsysuser);

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    public String checkPlatSysNameUnique(String loginName);
    /**
     * 校验手机验证码
     *
     * @param
     * @return
     */
    public String checkPhoneUnique(Platsystemsysuser platsystemsysuser);

    /**
     * 查询身份证是否唯一
     * @param
     * @return
     */
    public int checkCardUnique(String sysuserIdCardNo);
}
