package com.asuis.j2ee.mongodb.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author 15988440973
 * 审核信息
 * 包含理由
 */
@Document
public class AuditMessage {
    @Id
    private
    ObjectId _id;
    /**审核意见*/
    private String suggest;
    /**审核人*/
    private String auditor;
    /**审核人头像*/
    private String auditorAvatar;
    /**审核人id*/
    private String auditorId;
    /**审核时间*/
    private Date auditTime;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditorAvatar() {
        return auditorAvatar;
    }

    public void setAuditorAvatar(String auditorAvatar) {
        this.auditorAvatar = auditorAvatar;
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
