package com.ruoyi.third.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取代码生成相关配置
 *
 * @author wujiyue
 */
@Component
@PropertySource(value = {"classpath:third.properties"})
public class ThirdConfig {

    /**
     * 腾讯位置服务配置KEY
     */
    public static String tencentIpKey;
    /**
     * 百度人工智能AK
     */
    public static String baiduAiAk;

    /**
     * 百度人工智能SK
     */
    public static String baiduAiSk;
    /**
     * 腾讯验证码服务appId
     */
    public static String tencentCaptchaAppId;
    /**
     * 腾讯验证码服务appSecretKey
     */
    public static String tencentCaptchaAppSecretKey;
    /**
     * 乐云短信服务帐号
     */
    public static String smsLeYunAccount;
    /**
     * 乐云短信服务密码
     */
    public static String smsLeYunPassword;
    /**
     * 华软短信服务发送接口url
     * cjr 20200622新增
     */
    public static String smsHrSendUrl;
    /**
     * 华软短信服务账号
     * cjr 20200622新增
     */
    public static String smsHrAccount;
    /**
     * 华软短信服务密码
     * cjr 20200622新增
     */
    public static String smsHrPassword;
    /**
     * 华软短信服务签名
     * cjr 20200622新增
     */
    public static String smsHrPrefix;
    /**
     * 华软短信服务验证码网关ID
     * cjr 20200622新增
     */
    public static String smsHrGwId;

    /**
     * 百度推送cookie.自行登录百度站长平台后获取响应的cookie
     */
    public static String baiduPushCookie;
    /**
     * 百度推送使用的token
     */
    public static String baiduPushToken;
    public static String baiduPushDomain;


    @Value("${third.baidu.push.domain}")
    public void setBaiduPushDomain(String baiduPushDomain) {
        ThirdConfig.baiduPushDomain = baiduPushDomain;
    }

    @Value("${third.baidu.push.token}")
    public void setBaiduPushToken(String baiduPushToken) {
        ThirdConfig.baiduPushToken = baiduPushToken;
    }

    @Value("${third.baidu.push.cookie}")
    public void setBaiduPushCookie(String baiduPushCookie) {
        ThirdConfig.baiduPushCookie = baiduPushCookie;
    }


    @Value("${third.tencent.ip_key}")
    public void setTencentIpKey(String tencentIpKey) {
        ThirdConfig.tencentIpKey = tencentIpKey;
    }

    @Value("${third.baidu.ai.AK}")
    public void setBaiduAiAk(String baiduAiAk) {
        ThirdConfig.baiduAiAk = baiduAiAk;
    }

    @Value("${third.baidu.ai.SK}")
    public void setBaiduAiSk(String baiduAiSk) {
        ThirdConfig.baiduAiSk = baiduAiSk;
    }

    @Value("${third.tencent.tencentCaptcha.appId}")
    public void setTencentCaptchaAppId(String tencentCaptchaAppId) {
        ThirdConfig.tencentCaptchaAppId = tencentCaptchaAppId;
    }

    @Value("${third.tencent.tencentCaptcha.appSecretKey}")
    public void setTencentCaptchaAppSecretKey(String tencentCaptchaAppSecretKey) {
        ThirdConfig.tencentCaptchaAppSecretKey = tencentCaptchaAppSecretKey;
    }

    @Value("${third.sms.LeYun.account}")
    public void setSmsLeYunAccount(String smsLeYunAccount) {
        ThirdConfig.smsLeYunAccount = smsLeYunAccount;
    }

    @Value("${third.sms.LeYun.password}")
    public void setSmsLeYunPassword(String smsLeYunPassword) {
        ThirdConfig.smsLeYunPassword = smsLeYunPassword;
    }

    /**
     * cjr 20200622 新增
     *
     * @param smsHrSendUrl
     */
    @Value("${third.sms.HR.send.url}")
    public void setSmsHrSendUrl(String smsHrSendUrl) {
        ThirdConfig.smsHrSendUrl = smsHrSendUrl;
    }

    @Value("${third.sms.HR.account}")
    public void setSmsHrAccount(String smsHrAccount) {
        ThirdConfig.smsHrAccount = smsHrAccount;
    }

    @Value("${third.sms.HR.password}")
    public void setSmsHrPassword(String smsHrPassword) {
        ThirdConfig.smsHrPassword = smsHrPassword;
    }

    @Value("${third.sms.HR.send.prefix}")
    public void setSmsHrPrefix(String smsHrPrefix) {
        ThirdConfig.smsHrPrefix = smsHrPrefix;
    }

    @Value("${third.sms.HR.send.gwId}")
    public void setSmsHrGwId(String smsHrGwId) {
        ThirdConfig.smsHrGwId = smsHrGwId;
    }

    public static String getTencentIpKey() {
        return tencentIpKey;
    }

    public static String getBaiduAiAk() {
        return baiduAiAk;
    }

    public static String getBaiduAiSk() {
        return baiduAiSk;
    }

    public static String getTencentCaptchaAppId() {
        return tencentCaptchaAppId;
    }

    public static String getTencentCaptchaAppSecretKey() {
        return tencentCaptchaAppSecretKey;
    }

    public static String getSmsHrSendUrl() {
        return smsHrSendUrl;
    }

    public static String getSmsLeYunAccount() {
        return smsLeYunAccount;
    }

    public static String getSmsLeYunPassword() {
        return smsLeYunPassword;
    }

    public static String getSmsHrAccount() {
        return smsHrAccount;
    }

    public static String getSmsHrPassword() {
        return smsHrPassword;
    }

    public static String getSmsHrPrefix() {
        return smsHrPrefix;
    }

    public static String getSmsHrGwId() {
        return smsHrGwId;
    }

    public static String getBaiduPushCookie() {
        return baiduPushCookie;
    }

    public static String getBaiduPushToken() {
        return baiduPushToken;
    }

    public static String getBaiduPushDomain() {
        return baiduPushDomain;
    }
}
