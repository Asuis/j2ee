package com.asuis.j2ee.mongodb.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 管理员请求
 * 请求包括
 * 部门添加
 * 部门删除
 * 人员添加
 * 人员删除
 * @author 15988440973
 */
@Document
public class RequestMessage {
    @Id
    private ObjectId _id;
    /**请求人*/
    private String applicant;
    /**请求人头像*/
    private String applicantAvatar;
    /**请求人id*/
    private String applicantId;
    /**请求理由*/
    private String reason;
    /**请求要求*/
    private String action;
    /**请求时间*/
    private String requestTime;
    /**当前请求状态 已审核、未审核、未提交*/
    private String status;

    private AuditMessage auditMessage;

    public AuditMessage getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(AuditMessage auditMessage) {
        this.auditMessage = auditMessage;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplicantAvatar() {
        return applicantAvatar;
    }

    public void setApplicantAvatar(String applicantAvatar) {
        this.applicantAvatar = applicantAvatar;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}