package com.asuis.j2ee.service.impl;

import com.asuis.j2ee.dto.PageRequest;
import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.mongodb.domain.ActionRecord;
import com.asuis.j2ee.mongodb.domain.LoginRecord;
import com.asuis.j2ee.mongodb.domain.RequestMessage;
import com.asuis.j2ee.mongodb.domain.SaleRecord;
import com.asuis.j2ee.mongodb.repository.ActionRecordRepository;
import com.asuis.j2ee.mongodb.repository.NoticeMessageRepository;
import com.asuis.j2ee.mongodb.repository.RequestMessageRepository;
import com.asuis.j2ee.service.ObserverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 15988440973
 */
@Service
public class ObserverServiceImpl implements ObserverService {

    @Autowired
    private ActionRecordRepository actionRecordRepository;

    @Autowired
    private RequestMessageRepository requestMessageRepository;

    @Override
    public List<RequestMessage> getAuditRecode(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public Result getLoginRecode(Integer pageNum, Integer pageSize) {
        Result result =new Result();
        Sort sort = new Sort(Sort.Direction.DESC,"operatorId");
        Pageable pageable = new PageRequest(pageNum-1,pageSize,sort);
        try {
            Page<ActionRecord> data = actionRecordRepository.findByActionEquals("LOGIN",pageable);
            result.setCode(200);
            result.setData(data);
            result.setMessage("hello");
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage("啊啊eroror");
        }
        return result;
    }

    @Override
    public SaleRecord getSalePerformance(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public Result getActionRecode(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Sort sort = new Sort(Sort.Direction.DESC,"time");
        Pageable pageable = new PageRequest(pageNum-1,pageSize,sort);
        try {
            Page<ActionRecord> data = actionRecordRepository.findAll(pageable);
            result.setCode(200);
            result.setData(data);
            result.setMessage("hello");
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage("啊啊eroror");
        }
        return result;
    }

    @Override
    public List<SaleRecord> getDepartmentRank(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<SaleRecord> getSalePerformancePersional(Integer pageNum, Integer pageSize) {
        return null;
    }
}
