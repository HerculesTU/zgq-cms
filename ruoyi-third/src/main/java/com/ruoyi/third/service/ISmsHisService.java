package com.ruoyi.third.service;

import com.ruoyi.third.domain.SmsHis;
import java.util.List;

/**
 * 短信发送历史Service接口
 * 
 * @author wujiyue
 * @date 2019-10-11
 */
public interface ISmsHisService 
{
    /**
     * 查询短信发送历史
     * 
     * @param id 短信发送历史ID
     * @return 短信发送历史
     */
    public SmsHis selectSmsHisById(Long id);

    /**
     * 查询短信发送历史列表
     * 
     * @param smsHis 短信发送历史
     * @return 短信发送历史集合
     */
    public List<SmsHis> selectSmsHisList(SmsHis smsHis);

    /**
     * 新增短信发送历史
     * 
     * @param smsHis 短信发送历史
     * @return 结果
     */
    public int insertSmsHis(SmsHis smsHis);

    /**
     * 修改短信发送历史
     * 
     * @param smsHis 短信发送历史
     * @return 结果
     */
    public int updateSmsHis(SmsHis smsHis);

    /**
     * 批量删除短信发送历史
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsHisByIds(String ids);

    /**
     * 删除短信发送历史信息
     * 
     * @param id 短信发送历史ID
     * @return 结果
     */
    public int deleteSmsHisById(Long id);


    /**
     * 选择固定分钟内的数据且降序排列
     *
     * @param
     * @return 短信发送历史集合
     */
    public List<SmsHis> selectSmsHisList2(String phone,String sjc);
}
