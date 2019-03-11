package com.carter.sbdemo.model;

import java.util.Date;

public class TblChannelDTO {
    private String id;

    private String channelCooperationId;

    private String parent;

    private Integer chlevel;

    private String type;

    private String comid;

    private String name;

    private String companyName;

    private String leader;

    private String secretkey;

    private String isAuto;

    private Date onDate;

    private Date offDate;

    private String description;

    private String bankB;

    private String accountNameB;

    private String accountCodeB;

    private Date signDate;

    private String bankA;

    private String accountNameA;

    private String accountCodeA;

    private String status;

    private String operatorId;

    private String operatorName;

    private Date createTime;

    private Date operatorTime;

    private Long level;

    private String isAes;

    private Date tModifyTm;

    private String certType;

    private String certCode;

    private String syncState;

    private String recordNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getChannelCooperationId() {
        return channelCooperationId;
    }

    public void setChannelCooperationId(String channelCooperationId) {
        this.channelCooperationId = channelCooperationId == null ? null : channelCooperationId.trim();
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }

    public Integer getChlevel() {
        return chlevel;
    }

    public void setChlevel(Integer chlevel) {
        this.chlevel = chlevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid == null ? null : comid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey == null ? null : secretkey.trim();
    }

    public String getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(String isAuto) {
        this.isAuto = isAuto == null ? null : isAuto.trim();
    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

    public Date getOffDate() {
        return offDate;
    }

    public void setOffDate(Date offDate) {
        this.offDate = offDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getBankB() {
        return bankB;
    }

    public void setBankB(String bankB) {
        this.bankB = bankB == null ? null : bankB.trim();
    }

    public String getAccountNameB() {
        return accountNameB;
    }

    public void setAccountNameB(String accountNameB) {
        this.accountNameB = accountNameB == null ? null : accountNameB.trim();
    }

    public String getAccountCodeB() {
        return accountCodeB;
    }

    public void setAccountCodeB(String accountCodeB) {
        this.accountCodeB = accountCodeB == null ? null : accountCodeB.trim();
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getBankA() {
        return bankA;
    }

    public void setBankA(String bankA) {
        this.bankA = bankA == null ? null : bankA.trim();
    }

    public String getAccountNameA() {
        return accountNameA;
    }

    public void setAccountNameA(String accountNameA) {
        this.accountNameA = accountNameA == null ? null : accountNameA.trim();
    }

    public String getAccountCodeA() {
        return accountCodeA;
    }

    public void setAccountCodeA(String accountCodeA) {
        this.accountCodeA = accountCodeA == null ? null : accountCodeA.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getIsAes() {
        return isAes;
    }

    public void setIsAes(String isAes) {
        this.isAes = isAes == null ? null : isAes.trim();
    }

    public Date gettModifyTm() {
        return tModifyTm;
    }

    public void settModifyTm(Date tModifyTm) {
        this.tModifyTm = tModifyTm;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode == null ? null : certCode.trim();
    }

    public String getSyncState() {
        return syncState;
    }

    public void setSyncState(String syncState) {
        this.syncState = syncState == null ? null : syncState.trim();
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo == null ? null : recordNo.trim();
    }
}