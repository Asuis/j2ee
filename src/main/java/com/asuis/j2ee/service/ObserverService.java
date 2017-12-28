package com.asuis.j2ee.service;

import com.asuis.j2ee.mongodb.domain.ActionRecord;
import com.asuis.j2ee.mongodb.domain.LoginRecord;
import com.asuis.j2ee.mongodb.domain.RequestMessage;
import com.asuis.j2ee.mongodb.domain.SaleRecord;

import java.util.List;

/**
 * 观测者服务（老总）
 * 观看审核记录、未审核记录、登录记录、业绩图、业绩排名、查看任何人的操作记录
 * @author 15988440973
 */
public interface ObserverService {
    /**获取审核记录、需要分页、按条件排序、是否已审核*/
    public List<RequestMessage> getAuditRecode(Integer pageNum,Integer pageSize);
    /**获取登录（签到记录）默认按时间排序*/
    public List<LoginRecord> getLoginRecode(Integer pageNum, Integer pageSize);
    /**获取上月总业绩、当前月业绩*/
    public SaleRecord getSalePerformance(Integer pageNum, Integer pageSize);
    /**获取操作记录默认按时间排序，可选择部门，或直接查看某人的操作记录，记录包括ip地址、mac地址、对应的请求信息*/
    public List<ActionRecord> getActionRecode(Integer pageNum, Integer pageSize);
    /**查看各个总业绩部门业绩排名及业绩*/
    public List<SaleRecord> getDepartmentRank(Integer pageNum, Integer pageSize);
    /**获取个人业绩排名*/
    public List<SaleRecord> getSalePerformancePersional(Integer pageNum, Integer pageSize);
}
