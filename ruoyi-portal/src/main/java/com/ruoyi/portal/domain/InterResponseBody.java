package com.ruoyi.portal.domain;

public class InterResponseBody {
    private String id;

    private String fid;

    private String flowtoken;

    private String code;

    private String success;

    private String nextsteplist;

    private String nextaudittype;

    private String jbpmexeid;

    private String creattime;

    private Integer bs;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getFlowtoken() {
        return flowtoken;
    }

    public void setFlowtoken(String flowtoken) {
        this.flowtoken = flowtoken == null ? null : flowtoken.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success == null ? null : success.trim();
    }

    public String getNextsteplist() {
        return nextsteplist;
    }

    public void setNextsteplist(String nextsteplist) {
        this.nextsteplist = nextsteplist == null ? null : nextsteplist.trim();
    }

    public String getNextaudittype() {
        return nextaudittype;
    }

    public void setNextaudittype(String nextaudittype) {
        this.nextaudittype = nextaudittype == null ? null : nextaudittype.trim();
    }

    public String getJbpmexeid() {
        return jbpmexeid;
    }

    public void setJbpmexeid(String jbpmexeid) {
        this.jbpmexeid = jbpmexeid == null ? null : jbpmexeid.trim();
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime == null ? null : creattime.trim();
    }

    public Integer getBs() {
        return bs;
    }

    public void setBs(Integer bs) {
        this.bs = bs;
    }
}