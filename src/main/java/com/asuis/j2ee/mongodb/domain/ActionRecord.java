package com.asuis.j2ee.mongodb.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 用于记录操作记录
 * @author 15988440973
 */
@Document
public class ActionRecord {
    @Id
    private String _id;
    /**描述信息*/
    private String desc;
    /**操作 登陆 提交申请、审核申请*/
    private String action;

    private String departName;
    /**操作人*/
    private String operator;
    /**操作人头像*/
    private String operatorAvatar;
    /**操作id*/
    private String operatorId;
    private String ip;
    private String mac;
    private Date time;

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorAvatar() {
        return operatorAvatar;
    }

    public void setOperatorAvatar(String operatorAvatar) {
        this.operatorAvatar = operatorAvatar;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
}
