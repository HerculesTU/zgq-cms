package com.ruoyi.portal.domain;

public class ResponseNextStep {
    private String branchSelectKey;
    private String isBranch;
    private String nodeKeys;
    private String nodeNames;

    public String getBranchSelectKey() {
        return branchSelectKey;
    }

    public void setBranchSelectKey(String branchSelectKey) {
        this.branchSelectKey = branchSelectKey;
    }

    public String getIsBranch() {
        return isBranch;
    }

    public void setIsBranch(String isBranch) {
        this.isBranch = isBranch;
    }

    public String getNodeKeys() {
        return nodeKeys;
    }

    public void setNodeKeys(String nodeKeys) {
        this.nodeKeys = nodeKeys;
    }

    public String getNodeNames() {
        return nodeNames;
    }

    public void setNodeNames(String nodeNames) {
        this.nodeNames = nodeNames;
    }
}
