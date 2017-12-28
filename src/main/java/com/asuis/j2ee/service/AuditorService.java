package com.asuis.j2ee.service;

import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.mongodb.domain.AuditMessage;

/**
 * 关于审核记录应该存入队列 进行分发 防止审核重复
 * 审核服务
 * 判定管理员的申请 同时提交审核意见
 * 查询待审核的申请
 * 查询审核记录
 * 查询已审核记录
 * @author 15988440973
 */
public interface AuditorService {
    public Result getRequestNeedAudit(Integer pageNum,Integer pageSize);
    public Result auditRequest(AuditMessage auditMessage,String requestId);
    public Result getRequestByStatusAndAuditor(String userId,String status,Integer pageNum,Integer pageSize);
    public Result getRequestByStatus(String status,Integer pageNum,Integer pageSize);
}
