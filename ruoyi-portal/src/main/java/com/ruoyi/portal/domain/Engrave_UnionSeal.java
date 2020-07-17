package com.ruoyi.portal.domain;
/**
 * 第4步刻制工会公章实体
 */
public class Engrave_UnionSeal {

    private String ENTERPRISE_ID;
    private String OPERATOR_CONTACTS;
    private String UNION_PRESIDENT_IDCARD;
    private String CORPORATE_IDENTITY_CARD;
    private String ELECTRONIC_VERSION;
    private String IDENTITY_CARD_AGENT;
    private String exeId;
    private String formNodeKey;
    private String file;

    public String getExeId() {
        return exeId;
    }

    public void setExeId(String exeId) {
        this.exeId = exeId;
    }

    public String getFormNodeKey() {
        return formNodeKey;
    }

    public void setFormNodeKey(String formNodeKey) {
        this.formNodeKey = formNodeKey;
    }
    public String getOPERATOR_CONTACTS() {
        return OPERATOR_CONTACTS;
    }

    public void setOPERATOR_CONTACTS(String OPERATOR_CONTACTS) {
        this.OPERATOR_CONTACTS = OPERATOR_CONTACTS;
    }

    public String getUNION_PRESIDENT_IDCARD() {
        return UNION_PRESIDENT_IDCARD;
    }

    public void setUNION_PRESIDENT_IDCARD(String UNION_PRESIDENT_IDCARD) {
        this.UNION_PRESIDENT_IDCARD = UNION_PRESIDENT_IDCARD;
    }

    public String getCORPORATE_IDENTITY_CARD() {
        return CORPORATE_IDENTITY_CARD;
    }

    public void setCORPORATE_IDENTITY_CARD(String CORPORATE_IDENTITY_CARD) {
        this.CORPORATE_IDENTITY_CARD = CORPORATE_IDENTITY_CARD;
    }

    public String getELECTRONIC_VERSION() {
        return ELECTRONIC_VERSION;
    }

    public void setELECTRONIC_VERSION(String ELECTRONIC_VERSION) {
        this.ELECTRONIC_VERSION = ELECTRONIC_VERSION;
    }

    public String getIDENTITY_CARD_AGENT() {
        return IDENTITY_CARD_AGENT;
    }

    public void setIDENTITY_CARD_AGENT(String IDENTITY_CARD_AGENT) {
        this.IDENTITY_CARD_AGENT = IDENTITY_CARD_AGENT;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getENTERPRISE_ID() {
        return ENTERPRISE_ID;
    }

    public void setENTERPRISE_ID(String ENTERPRISE_ID) {
        this.ENTERPRISE_ID = ENTERPRISE_ID;
    }
}
