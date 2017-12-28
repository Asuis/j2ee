package com.asuis.j2ee;

import com.asuis.j2ee.dto.PageRequest;
import com.asuis.j2ee.mongodb.domain.ActionRecord;
import com.asuis.j2ee.mongodb.domain.AuditMessage;
import com.asuis.j2ee.mongodb.domain.NoticeMessage;
import com.asuis.j2ee.mongodb.domain.RequestMessage;
import com.asuis.j2ee.mongodb.repository.ActionRecordRepository;
import com.asuis.j2ee.mongodb.repository.AuditMessageRepository;
import com.asuis.j2ee.mongodb.repository.NoticeMessageRepository;
import com.asuis.j2ee.mongodb.repository.RequestMessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTests {
    @Autowired
    ActionRecordRepository actionRecordRepository;
    @Autowired
    AuditMessageRepository auditMessageRepository;
    @Autowired
    NoticeMessageRepository noticeMessageRepository;
    @Autowired
    RequestMessageRepository requestMessageRepository;
    @Test
    public void testActionRecode() {
        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setAction("添加角色");
        actionRecord.setDesc("dkfjdkf");
        actionRecord.setOperator("操作人");
        actionRecord.setTime(new Date(System.currentTimeMillis()));
        actionRecord.setOperatorAvatar("?");
        actionRecord.setOperatorId("1");
        actionRecordRepository.insert(actionRecord);
    }
    @Test
    public void testAuditMessage() {
        AuditMessage auditMessage = new AuditMessage();
        auditMessage.setAuditor("Asuis");
        auditMessage.setAuditTime(new Date(System.currentTimeMillis()));
        auditMessage.setSuggest("ok");
        auditMessage.setAuditorAvatar("?");
        auditMessage.setAuditorId("1");
        auditMessageRepository.insert(auditMessage);
    }
    @Test
    public void testNoticeMessage() {
        NoticeMessage noticeMessage = new NoticeMessage();
        noticeMessage.setContent("hello");
        noticeMessage.setFrom("发信人");
        noticeMessage.setTo("收信人");
        noticeMessageRepository.insert(noticeMessage);
    }
    @Test
    public void testRequestMessage() {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setAction("add user");
        requestMessage.setApplicant("申请人");
        requestMessage.setApplicantAvatar("?");
        requestMessage.setApplicantId("1");
        requestMessage.setReason("reason");
        requestMessage.setStatus("0");

        AuditMessage auditMessage = new AuditMessage();

        auditMessage.setAuditor("Asuis");
        auditMessage.setAuditTime(new Date(System.currentTimeMillis()));
        auditMessage.setSuggest("ok");
        auditMessage.setAuditorAvatar("?");
        auditMessage.setAuditorId("1");

        requestMessage.setAuditMessage(auditMessage);
        requestMessageRepository.insert(requestMessage);
    }
    @Test
    public void testActionRecordRepository() {
        Pageable pageable = new PageRequest(1,10,null);
        Page<ActionRecord> data = actionRecordRepository.findBy_id_Date(new Date(System.currentTimeMillis()),pageable);
    }
    @Test
    public void testRequestMessageRepository() {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setStatus("0");
        requestMessage.setReason("claj");
        requestMessage.setApplicantId("1");
        requestMessage.setApplicantAvatar("?");
        requestMessage.setApplicant("Asuis");
        requestMessage.setAction("insert member");
        requestMessage.setRequestTime(new Date(System.currentTimeMillis()));

        RequestMessage a = requestMessageRepository.insert(requestMessage);

        AuditMessage auditMessage = new AuditMessage();
        auditMessage.setAuditorId("1");
        auditMessage.setAuditor("Asuis");
        auditMessage.setSuggest("好的");
        auditMessage.setAuditTime(new Date(System.currentTimeMillis()));
        auditMessage.setAuditorAvatar("???");

        a.setAuditMessage(auditMessage);

        a = requestMessageRepository.save(a);
        Pageable pageable = new PageRequest(1,10,new Sort(Sort.Direction.DESC,"_id"));
        List<RequestMessage> requestMessages = requestMessageRepository.findByQuery(a.getAuditMessage().getAuditorId());
        System.out.println(requestMessages.toString());
    }
}
